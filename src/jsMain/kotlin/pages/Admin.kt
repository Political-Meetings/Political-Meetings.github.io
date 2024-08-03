package fr.xibalba.politicalMeetings.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.onFocusOut
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.disclosure.TabPanel
import com.varabyte.kobweb.silk.components.disclosure.Tabs
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.TextInput
import fr.xibalba.politicalMeetings.AppCoroutineScope
import fr.xibalba.politicalMeetings.data.Meeting
import fr.xibalba.politicalMeetings.data.Party
import fr.xibalba.politicalMeetings.data.getParties
import fr.xibalba.politicalMeetings.data.setParties
import fr.xibalba.politicalMeetings.layouts.PageLayout
import fr.xibalba.politicalMeetings.utils.editFile
import fr.xibalba.politicalMeetings.utils.getSha
import fr.xibalba.politicalMeetings.utils.unaryPlus
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.Option
import org.jetbrains.compose.web.dom.Select
import org.w3c.dom.HTMLSelectElement
import web.storage.localStorage

@Composable
@Page
fun Admin() {
    var token by remember { mutableStateOf(localStorage.getItem("token") ?: "") }
    val ctx = rememberPageContext()
    var tokenValid by remember { mutableStateOf(false) }
    var parties by remember { mutableStateOf(emptyList<Party>()) }
    AppCoroutineScope.launch {
        tokenValid = checkToken(token)
    }
    AppCoroutineScope.launch {
        parties = getParties()
    }
    PageLayout("Admin") {
        Row {
            TextInput(token, placeholder = "token github", onTextChange = {
                token = it
            })
            Button(onClick = {
                AppCoroutineScope.launch {
                    tokenValid = checkToken(token)
                }
            }) {
                +"Vérifier"
            }
            if (!tokenValid) {
                Button(onClick = {
                    ctx.router.navigateTo("https://github.com/settings/personal-access-tokens/new")
                }) {
                    +"Générer"
                }
            }
        }
        if (tokenValid) {
            Tabs {
                TabPanel("Partis") {
                    Tabs {
                        TabPanel("Liste") {
                            if (parties.isEmpty()) {
                                +"Aucun parti"
                            } else {
                                Column {
                                    parties.forEach {
                                        +it.name
                                    }
                                }
                            }
                        }
                        TabPanel("Ajouter") {
                            var partyName by remember { mutableStateOf("") }
                            Row {
                                TextInput(partyName, placeholder = "Nom du parti", onTextChange = {
                                    partyName = it
                                })
                                Button(onClick = {
                                    AppCoroutineScope.launch {
                                        editFile(token, "Api/$partyName.party.json", Party(partyName, emptyList()))
                                        editFile(token, "Api/parties.json", parties.map { it.name } + partyName)
                                        parties += Party(partyName, emptyList())
                                        setParties(parties)
                                        partyName = ""
                                    }
                                }, enabled = partyName.isNotEmpty()) {
                                    +"Ajouter"
                                }
                            }
                        }
                    }
                }
                TabPanel("Meetings") {
                    if (parties.isEmpty()) {
                        +"Aucun parti"
                    } else {
                        Tabs {
                            TabPanel("Liste") {
                                if (parties.none { it.meetings.isNotEmpty() }) {
                                    +"Aucun meeting"
                                } else {
                                    Column {
                                        parties.filter { it.meetings.isNotEmpty() }.forEach {
                                            +it.name
                                            it.meetings.forEach {
                                                +"${it.date} - ${it.location}"
                                            }
                                        }
                                    }
                                }
                            }
                            TabPanel("Ajouter") {
                                var partyName by remember { mutableStateOf(parties.first().name) }
                                var date by remember { mutableStateOf("") }
                                var location by remember { mutableStateOf("") }
                                Row {
                                    Select(attrs = Modifier.id("party").onFocusOut {
                                        partyName = (it.target as HTMLSelectElement).value
                                        console.log(partyName)
                                    }.toAttrs()) {
                                        for (party in parties) {
                                            Option(value = party.name) {
                                                +party.name
                                            }
                                        }
                                    }
                                    TextInput(date, placeholder = "Date", onTextChange = {
                                        date = it
                                    })
                                    TextInput(location, placeholder = "Lieu", onTextChange = {
                                        location = it
                                    })
                                    Button(onClick = {
                                        AppCoroutineScope.launch {
                                            val party = parties.find { it.name == partyName }!!
                                            val meetings = party.meetings + Meeting(date, location)
                                            editFile(token, "Api/$partyName.party.json", Party(partyName, meetings))
                                            setParties(parties.map {
                                                if (it.name == partyName) {
                                                    Party(partyName, meetings)
                                                } else {
                                                    it
                                                }
                                            })
                                            parties = getParties()
                                            date = ""
                                            location = ""
                                        }
                                    }, enabled = date.isNotEmpty() && location.isNotEmpty()) {
                                        +"Ajouter"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

suspend fun checkToken(token: String): Boolean {
    try {
        val response = getSha(token, "Api/parties.json")
        if (response.isNotEmpty()) {
            localStorage.setItem("token", token)
            return true
        } else {
            return false
        }
    } catch (e: Exception) {
        return false
    }
}