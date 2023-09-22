/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author Manuel
 */
public class Lista {
    
    NodoLE inicio; //CREO UNA LISTA
    int tamano;
    	
  public Lista() //SE INICIALIZA LA LISTA 
  {
  	inicio = null;
  	tamano = 0;
  }
 
  public int tamano() //TAMAÑO DE LA LISTA
  {
  	return this.tamano;
  }
  
  public boolean llena()//para saber si hay memo suficiente
  {
      try
      {
          NodoLE x = new NodoLE();
      }
      catch (Exception e){
          
          System.out.println("No hay Memoria suficiente");
          return true;
      }
    return false;
  }
  
  public void insOrden(int val,char let)//inserta en la lista en orden ascendente
  {
	NodoLE aux = inicio;
	NodoLE ant = null;
	while(aux != null && aux.frecuencia <= val)
	{
            ant = aux;
            aux = aux.sig;		
	}
	NodoLE nuevo = new NodoLE();
	nuevo.frecuencia = val;
        nuevo.letra = let;
	nuevo.sig = aux;
        
	if(ant == null){
            inicio = nuevo;
        }
        else{
            ant.sig = nuevo;
        }
	this.tamano++;
  }
    
  public void sumaNodos() //SUMA LAS FRECUENCIAS DE LOS NOSDOS DE DOS EN DOS HASTA QUE SOLO QUEDA UNO
  {
      NodoLE aux = inicio.sig;
      NodoLE ant = inicio;
      while(aux != null)
      {
          inserta(aux.frecuencia + ant.frecuencia, '*', ant, aux); 
          ant = aux.sig;
          aux = aux.sig.sig;				  
      }            
  }
  
  public void inserta(int val,char caracter,NodoLE izq,NodoLE der)//inserta solo alfinal de la lista
  {
        NodoLE aux = inicio;
        NodoLE ant = null;
	
	while(aux != null)
	{
            ant = aux;
            aux = aux.sig;				
	}
        //creo el nuevo nodo a ser insertado
	NodoLE nuevo = new NodoLE();
        
	nuevo.frecuencia = val;
        nuevo.letra = caracter;
	nuevo.sig = aux;
        nuevo.izq = izq;
        nuevo.der = der;      
	this.tamano++;
        
	if(ant == null){
            inicio = nuevo;
        }else{
            ant.sig = nuevo;
        }          
  }
  
  public String inorden(NodoLE n) //metode del arbol para imprimir en inorden +el arbol no es binario
  {
      String cad = " ";
      if(n == null){
          return cad;
      }
      else
      {
          cad = inorden(n.izq) + "";
          cad = cad + n.frecuencia;
          cad = cad + inorden(n.der) + "";

      return cad;
      }
    }
 
  public String recorrido(NodoLE n,String cod,char letra) //metodo que genera los digitos huffman
  {
      String cad = "";
      
        if(n != null){
            if(n.izq != null){
                cad = recorrido(n.izq, cod + "0", letra);
            }
            if(n.letra == letra){
                return cod;
            }
            else{
                if(cad == ""){
                    return recorrido(n.der, cod + "1", letra);
                }
                else{
                    return cad;
                }
            }            
        }
        else{
            return "";
        }    
  }  
  public NodoLE obtenerArbol() //DEVUELVE EL ULTIMO NODO DE LA LISTA QUE ES EL ARBOL TERMINADO
  {
      int n = 0;
	NodoLE aux = inicio;
	NodoLE ant = null;
	
	while (aux != null)
	{
            ant = aux;
            aux = aux.sig;
            n++;
	}	
        return ant;
  }
  public void comprime(NodoLE arbol,String mensaje){ //COMPRIME EL MENSAJE
      
      System.out.println("==========CODIGO COMPRIMIDO HUFFMAN========");
      for(int i = 0; i < mensaje.length(); i++){
            System.out.print(recorrido(arbol,"",mensaje.charAt(i) ));
      }
      System.out.println();
  }
  
  public void descomprime(NodoLE arbol,String huffman){
      
      NodoLE aux = arbol;
      String cad = "";
      int c = 0;
      while(c < huffman.length()){
          
          if(huffman.charAt(c) == '0'){
              
              if(aux.izq != null){                  
                  aux = aux.izq;
                  c++;
              }
              else{
                  cad = cad + aux.letra;
                  aux = arbol;                  
              }              
          }
          else{
              if(huffman.charAt(c) == '1'){
                  
                  if(aux.der != null){
                      aux = aux.der;
                      c++;
                 }
                 else{
                      cad = cad + aux.letra;
                      aux = arbol;                      
                }              
             }              
          }
          
      }
      System.out.println("==========CODIGO DESCOMPRIMIDO HUFFMAN==========");
      System.out.println(cad + aux.letra);      
  }
  
  public void tabla(String mensaje,NodoLE arbol){ //imprimo la tabla de codigo
      System.out.printf("\n%s\n%s%10s\n","===TABLA DE CODIGO===","caracter","codigo ");      
       for(int i = 0; i < mensaje.length(); i++){
            System.out.printf("%5c%10s\n",mensaje.charAt(i),recorrido(arbol,"",mensaje.charAt(i)));
      }
      System.out.println("===========================\n"); 
  }
  
  public String toString()//solo imprime la lista
  {
      String cad=">>>>>>>>>>>>Nodos Huffman<<<<<<<<<<<\n";
      NodoLE nuevo;
      nuevo = inicio;
      while(nuevo!=null)
      {
          cad = cad + "["+nuevo.letra+"|"+nuevo.frecuencia+"]"+"->";
          nuevo = nuevo.sig;  		 		
      }
      
      return cad;
  }    
}
