package com.example.cryptocurrency.presentation.coin_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.cryptocurrency.data.remote.dto.TeamMember
import com.example.cryptocurrency.presentation.ui.theme.CryptoCurrencyTheme


@Composable
fun TeamListItem(
    teamMember: TeamMember,
    modifier: Modifier=Modifier
)
{
    Column(
        modifier=modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = teamMember.name, style = MaterialTheme.typography.body1, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = teamMember.position, style = MaterialTheme.typography.body2, fontStyle = FontStyle.Italic)
    }
}

class TeamListItemProvider: PreviewParameterProvider<TeamMember>
{
    override val values: Sequence<TeamMember>
        get() = listOf<TeamMember>(TeamMember("1","meric","CEO"), TeamMember("2","enes","CTO")).asSequence()

}

@Preview(showBackground = true)
@Composable
fun TeamListItemPreview(
    @PreviewParameter(TeamListItemProvider::class) teamMember:TeamMember
)
{
    CryptoCurrencyTheme {
        TeamListItem(teamMember = teamMember)
    }
}