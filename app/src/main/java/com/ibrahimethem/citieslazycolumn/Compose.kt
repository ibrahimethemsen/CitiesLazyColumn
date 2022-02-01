package com.ibrahimethem.citieslazycolumn

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun CityCard(
    cityName: String,
    cityDescription: String,
    contentDescription: String,
    cityImage: Int
) {
    Card(
        modifier = Modifier
            .padding(5.dp) //5 dp lik boşluk bırakıyoruz
            .fillMaxWidth() //genişlik olarak tamamını kaplayacak
            .wrapContentHeight(), //içerisindeki görünümler kadar yüksekliğe sahip olucak
        shape = MaterialTheme.shapes.medium, // kartın şeklini ve gölgesini tanımlar gölge elevation 0 dan büyükse gözükür
        elevation = 2.dp, //kartın altındaki gölge boyutunu kontrol eder
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = cityImage),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(130.dp)
                    .padding(5.dp),
                contentScale = ContentScale.Fit
            )

            Column(
                Modifier.padding(8.dp)
            ) {
                Text(
                    text = cityName,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface
                )

                Text(
                    text = cityDescription,
                    style = MaterialTheme.typography.body2
                )
            }

        }
    }
}


@Composable
fun AllCities(cityList: List<City>) {
    //Scaffold -> en genel çerçeveyi netleştirebileceğimiz bir yapı topbar parametresi verdik
    Scaffold(
        topBar = {
            TopAppBar(
                //TopAppBar arka plan rengi
                backgroundColor = MaterialTheme.colors.primary,
                //TopAppBar başlığı bunun için Text oluşturduk ve metinimizi verdik
                title = {
                    Text(
                        text = "Şehirler Uygulaması"
                    )
                }
            )
        },
    ) {
        //LazyColumn oluşturduk
        LazyColumn(
            //ekranda yayılabildiği kadar yayılmasını istedik
            modifier = Modifier.fillMaxWidth(),
            //kenarlara yapışmaması için 15dp lik bir boşluk bıraktık
            contentPadding = PaddingValues(15.dp)
        ) {
            //en üstte bir başlık olmasını istiyoruz bunun için item ekledik
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //başlığımız
                    Text(
                        text = "\uD83C\uDFD9 Şehirler Uygulaması",
                        style = MaterialTheme.typography.h4
                    )
                }
            }
            //şehirler listemizi eklemek için items koyduk ve parametre olarak şehirlerimizi verdik
            items(cityList) { city ->
                //city -> bizim listemizdeki elemanların hepsini tek tek CityCard içine yerleştirip ekrana koyuyor
                CityCard(
                    cityName = city.cityName,
                    cityDescription = city.cityDescription,
                    contentDescription = city.contentDescription,
                    cityImage = city.cityImage
                )
            }
        }
    }
}