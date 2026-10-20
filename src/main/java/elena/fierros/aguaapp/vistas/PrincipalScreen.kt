package elena.fierros.aguaapp.vistas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import elena.fierros.aguaapp.R
import elena.fierros.aguaapp.viewmodels.AguaViewModel
import androidx.compose.foundation.lazy.items

@Composable
fun PrincipalScreen (
    viewModel: AguaViewModel,
    onBoton: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.titulo),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBoton, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.btn_enviar))
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.historial),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (viewModel.registros.isEmpty()){
            Text(text = stringResource(R.string.sin_registros))
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(viewModel.registros, key = {it.id}) { registro ->
                    RegistroCard(
                        registro = registro,
                        onRespuesta = {tomo -> viewModel.responder(registro.id,tomo)}
                    )

                }
            }
        }
    }
}