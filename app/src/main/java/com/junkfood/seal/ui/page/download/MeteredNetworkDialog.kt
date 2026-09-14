package com.junkfood.seal.ui.page.download

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SignalCellularConnectedNoInternet4Bar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.junkfood.seal.R
import com.junkfood.seal.ui.component.BottomButtonShape
import com.junkfood.seal.ui.component.MiddleButtonShape
import com.junkfood.seal.ui.component.ytclipDialogButtonVariant
import com.junkfood.seal.ui.component.ytclipDialogVariant
import com.junkfood.seal.ui.component.TopButtonShape

@Composable
@Preview
fun MeteredNetworkDialog(
    onDismissRequest: () -> Unit = {},
    onAllowOnceConfirm: () -> Unit = {},
    onAllowAlwaysConfirm: () -> Unit = {},
) {
    ytclipDialogVariant(
        onDismissRequest = onDismissRequest,
        icon = {
            Icon(
                imageVector = Icons.Outlined.SignalCellularConnectedNoInternet4Bar,
                contentDescription = null,
            )
        },
        //        text = {
        //            Text(
        //                text = stringResource(id = R.string.download_disabled_with_cellular),
        //                modifier = Modifier.padding(horizontal = 24.dp)
        //            )
        //        },
        title = { Text(text = stringResource(id = R.string.download_with_cellular_request)) },
        buttons = {
            ytclipDialogButtonVariant(
                text = stringResource(id = R.string.allow_always),
                shape = TopButtonShape,
            ) {
                onAllowAlwaysConfirm()
            }
            ytclipDialogButtonVariant(
                text = stringResource(id = R.string.allow_once),
                shape = MiddleButtonShape,
            ) {
                onAllowOnceConfirm()
            }
            ytclipDialogButtonVariant(
                text = stringResource(id = R.string.dont_allow),
                shape = BottomButtonShape,
            ) {
                onDismissRequest()
            }
        },
    )
}
