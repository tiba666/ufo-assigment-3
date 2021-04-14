# Letter frequencies
Simple program used to illustrate performance problems. You should be able to optimize this program to run about twice as fast.

specs of the laptop for the test 

win 8 64 bit  
version number 6.3 build 9600
cpu amd e1-6010 apu ( max 1.35 GHz) duel core
3,544 mb 800 mhz ddr3 ram


measurement the methode to find its bottleneck and optimite that part of the code..

like run a methode 1000 times and get run time for each run and get the avage run time and calculate the deviation.

we ran the default methode...



place to be optimized is only the default methode tallyChars in my chace i made a tallyChars2 which have the changes 

first i add an emtry string called temp.

after that i make a BufferedReader and convert the reader the mehtode get to a new bufferedreader with a buffer size on 10000. 

after that i change my while loop from "(b = reader.read()) != -1"  -> "(temp = breader.readLine()) != null"

and then wrap the try catch methode insite a for loop there look like this for (char c : temp.toCharArray()){
b = Character.getNumericValue(c);
try....catch... }

made the improvement to be over 50% 




picture comming when i get the real benchmark 

hypotesten var at reader.read() var nød til at kalde for hver lykke og det er en ret dyr fald da den har noget med en fil at gøre. og ved at lave en buffer "alokere noget mem" så kunne den tag det for memmory istedet for at load det i real time hele tiden 
og ved at lave buffer der tag hver linje i dok kunne jeg gøre det array igennem hurtigere. 

filen ligger på en langsomere tilgangsted sted "harddsik " hvor ram er tættet og nemmer adgang til cpuen 

billeder kommer når programmet køre på benchmark maskinen.

kommer senere i dag 

lav nogle grafer
