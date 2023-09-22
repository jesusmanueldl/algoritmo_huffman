/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author Manuel
 */
public class Huffman {

    /**
     * @param args the command line arguments
     */
       //arboles binarios de frecuencia ordenada
    public static void main(String[] args) {
        // TODO code application logic here    
        
       Lista l = new Lista();//CREO UNA LISTA DONDE SE GUARDARAN LOS NODOS
       
       //==================MENSAJE QUE SE DESEA COMPRIMIR==================
       String mensaje = "jesus"; 
       //==================================================================
       
       
       char[] arryChar = new char[mensaje.length()];   //PALABRA A COMPRIMIR
       //lleno el arrayChar
       for(int i = 0; i < mensaje.length(); i++){
           arryChar[i] = mensaje.charAt(i);
       }
       
       //===========RECORRO MI CADENA DE CARACTERES E INSERTO A LA LISTA LOS CARECTERES 
       System.out.println("MENSAJE: "+mensaje+"\n");
       System.out.printf("%s%s%15s\n","====TABLA DE FRECUENCIA====\n","caracter","frecuencia");
       for(int i = 0; i < arryChar.length; i++){  
            char letra =  arryChar[i];
            if(letra != '$'){
                int f = frecuencia(arryChar[i],arryChar);
                System.out.printf("%5c%13d\n",letra,f);//IMPRIMO LA TABLA DE FRECUENCIA
               l.insOrden(f,letra);   //INSERTO EN ORDEN A LA LISTA DE NODOS
            }                        
        }
        System.out.println("===========================\n");
        
        //===================IMPRIMO LA LISTA ENLAZADA==================== 
        System.out.println(l.toString());
        System.out.println("Tamanio de la lista > " + l.tamano());      
        l.sumaNodos(); //toma de dos en dos los nodos y los agrega al final a la lista de nodos
        System.out.println(l.toString());
        System.out.println("Tamanio de la lista > " + l.tamano());
        //=================================================================
        
        //=======================ARBOL====================================
        NodoLE arbol = l.obtenerArbol(); //OBTENGO EL ARBOL QUE ES EL ULTIMO DE LA LISTA.
        
        System.out.println("\n============Arbol=============\n" + l.inorden(arbol)); 
        System.out.println("==============================");
        //algoritmo de compresion y descompresion por huffman
        //============IMPRIMO LA TABLA DE CODIGOS=====================
        l.tabla(mensaje, arbol);
        //============================================================
        
        //====================COMPRESION==============
        l.comprime(arbol,mensaje); //==GENERA CODIGO HUFFMAN====
        //===================DESCOMPRESION===================
        //l.descomprime(arbol, "00");//SE INGRESA EL CODIGO GENERADO PARA DESCOMPRIMIR EL MENSAJE
       //==================================================================
        
    }
    //=======METODO QUE GENERA LAS FECUENCIA DE REPETICION=============
    public static int frecuencia(char letra,char[] cadena){      //FECUENCIA  
        int val = 0;        
        for(int i = 0; i < cadena.length; i++){
            if(letra != '$'){
               if(letra == cadena[i]){
                   cadena[i] = '$';
                   val++;
               }    
            }            
        }        
        return val;
    }
}
