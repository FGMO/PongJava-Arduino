long  potenciometro1;
long  potenciometro2;
int boton = 2;

void setup() {
 Serial.begin(9600);
 pinMode(boton, INPUT);
}

void loop() {
  raqueta();
 // iniciar();
  delay(100);
}

void raqueta(){
 potenciometro1 = analogRead(A0);
 potenciometro2 = analogRead(A1);
 Serial.write((255*potenciometro1)/1023);
 Serial.write((255*potenciometro2)/1023);  
 }

void iniciar(){
  int ini = digitalRead(boton);
  if(ini == 1){
  Serial.write(true);
  }
  }

