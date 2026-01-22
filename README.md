# ViewModel

##  Compose-tilanhallinta
Jetpack Compose käyttää reactiivista tilaa, eli UI päivittyy automaattisesti kun tila muuttuu.

##  Miksi ViewModel on parempi kuin pelkkä remember

- remember tallentaa tilan vain yhen composablen elinkaaren ajaksi.  
  Tilat katoaa esimerkiksi näytön kierron yhteydessä.
- ViewModel säilyttää tilan koko näytön elinkaaren ajan ja toimii myös useiden composablejen välillä.
- ViewModel erottaa tilan hallinnan UI:sta, se tekee sovelluksesta vakaamman ja testattavamman.
- Kaikki tehtävälistan toiminnot (add, toggle, remove, sort) kulkee ViewModelin kautta, eli UI pysyy yksinkertaisena ja reaktiivisena.





