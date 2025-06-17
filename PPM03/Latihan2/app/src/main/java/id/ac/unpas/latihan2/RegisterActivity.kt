package id.ac.unpas.latihan2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.unpas.latihan2.ui.theme.Latihan2Theme
import id.ac.unpas.latihan2.ui.theme.Pink40
import id.ac.unpas.latihan2.ui.theme.Purple80

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Latihan2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FormRegistrasi(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun FormRegistrasi(modifier: Modifier = Modifier) {
    val ctx = LocalContext.current

    //––– STATE UNTUK MASING‑MASING FIELD ––––––––––––––––––––––––––––––––––––––
    val nama       = remember { mutableStateOf(TextFieldValue()) }
    val username   = remember { mutableStateOf(TextFieldValue()) }
    val telepon    = remember { mutableStateOf(TextFieldValue()) }
    val email      = remember { mutableStateOf(TextFieldValue()) }
    val alamat     = remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)) {

        // Fungsi pembantu agar tidak repetitif
        @Composable
        fun LabeledField(label: String,
                         value: MutableState<TextFieldValue>,
                         keyboardOptions: KeyboardOptions = KeyboardOptions.Default) {
            Text(label, modifier = Modifier.padding(start = 4.dp, bottom = 2.dp))
            TextField(
                value = value.value,
                onValueChange = { value.value = it },
                keyboardOptions = keyboardOptions,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }

        LabeledField("Nama", nama)
        LabeledField("Username", username)
        LabeledField(
            "Nomor Telepon",
            telepon,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)   // 🡐 cocok untuk telp
        )
        LabeledField(
            "Email",
            email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)   // 🡐 cocok untuk email
        )
        LabeledField("Alamat Rumah", alamat)

        Spacer(Modifier.height(8.dp))

        val simpanColors = ButtonDefaults.buttonColors(
            containerColor = Purple80,
            contentColor   = Color.White
        )
        val resetColors  = ButtonDefaults.buttonColors(
            containerColor = Pink40,
            contentColor   = Color.White
        )

        Row(Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp),
                colors = simpanColors,
                onClick = {
                    // Validasi: semua field harus terisi
                    if (nama.value.text.isNotBlank()   &&
                        username.value.text.isNotBlank() &&
                        telepon.value.text.isNotBlank()  &&
                        email.value.text.isNotBlank()    &&
                        alamat.value.text.isNotBlank()) {
                        Toast.makeText(ctx, "Halo, ${nama.value.text}", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(ctx,
                            "Semua inputan harus diisi",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            ) {
                Text("Simpan", style = TextStyle(fontSize = 18.sp))
            }

            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp),
                colors = resetColors,
                onClick = {
                    nama.value      = TextFieldValue()
                    username.value  = TextFieldValue()
                    telepon.value   = TextFieldValue()
                    email.value     = TextFieldValue()
                    alamat.value    = TextFieldValue()
                }
            ) {
                Text("Reset", style = TextStyle(fontSize = 18.sp))
            }
        }
    }
}
