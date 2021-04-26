package ltd.evilcorp.epw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ltd.evilcorp.epw.ui.theme.EpwTheme
import kotlin.random.Random

private val EXAMPLE_ACCOUNTS = listOf(
    Account("GitHub", "username", "password", "https://github.com"),
    Account("GitLab", "username", "password", "https://gitlab.com"),
    Account("Gitea", "username", "password", "https://try.gitea.io/"),
    Account("sr.ht", "username", "password", "https://git.sr.ht/"),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EpwTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AccountList(EXAMPLE_ACCOUNTS)
                }
            }
        }
    }
}

data class Account(
    val title: String,
    val username: String,
    val password: String,
    val url: String,
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountRow(account: Account) {
    ListItem(
        text = { Text(account.title) },
        secondaryText = { Text(account.url) },
    )
}

@Composable
fun AccountList(accounts: List<Account>) {
    LazyColumn {
        items(accounts) { account ->
            if (accounts.indexOf(account) == 0) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Accounts",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
            AccountRow(account)

            // And add some more placeholder accounts so I can do some scrolling.
            if (accounts.indexOf(account) == accounts.size - 1) {
                val rand = Random(0)
                repeat(100) {
                    AccountRow(account.copy(title = rand.nextLong().toString()))
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AccountListPreview() {
    EpwTheme {
        AccountList(EXAMPLE_ACCOUNTS)
    }
}
