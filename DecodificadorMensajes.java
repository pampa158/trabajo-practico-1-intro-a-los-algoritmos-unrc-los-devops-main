import java.util.*;

/**
 * Clase DecodificadorMensajes: representa una componente capaz de descifrar
 * un mensaje en formato texto, dado el mensaje y el código usado para la 
 * encripción. El mensaje a descifrar/decodificar debe ser un objeto
 * de tipo Mensaje (básicamente, una lista de strings, donde cada string
 * representa una línea). Se asume que el mensaje es ASCII, es decir, todos
 * los caracteres del mensaje tienen códigos en el rango [0, 127].
 * 
 * La codificación/decodificación utiliza una variante de Cifrado Cesar, en 
 * el cual el desplazamiento se basa en una código de encripción múltiple. 
 * Véase Cifrado de Vigenère para más detalles.
 * 
 * @author N. Aguirre
 * @version 0.1
 */
public class DecodificadorMensajes
{
    /**
     * Mensaje codificado
     */
    private Mensaje mensajeADecodificar; //mensaje "lqle"
    
    /**
     * Código a utilizar
     */
    private int[] codigoEncripcion; //codigo arreglo {4, 2, 0}
    
    /**
     * Mensaje decodificado
     */
    private Mensaje mensajeDecodificado; // devuelve "hola"

    /**
     * Constructor de la clase DecodificadorMensajes.
     * Inicializa el mensaje a desencriptar/decodificar con el parámetro pasado, 
     * junto con el código de desencripción. 
     * Precondición: tanto el mensaje msg como el código codigo no pueden ser nulos
     * @param msg es el mensaje a desencriptar.
     * @param codigo es el código de desencripción.
     */
    public DecodificadorMensajes(Mensaje msg, int[] codigo)
    {
        if (msg == null)
            throw new IllegalArgumentException("Mensaje nulo");
        if (codigo == null)
            throw new IllegalArgumentException("Código inválido.");
        mensajeADecodificar = msg;
        codigoEncripcion = codigo;
        mensajeDecodificado = null;
    }

    /**
     * Desencripta el mensaje. El mensaje no debe estar desencriptado.
     * Precondición: El mensaje aún no fue descifrado (i.e., el campo 
     * mensajeDecodificado es null).
     */
    public void decodificarMensaje() 
    {
        //Precondición para chequear que el mensaje aun no fue decodificado
        /**     if(mensajeDecodificado != null)
           *        //mensaje ya decodificado
        *       throw new IllegalStateException("El mensajeaun no fue descifrado");
        */
        String cadena = mensajeADecodificar.toString();
        desencriptarCadenaV2(cadena, codigoEncripcion);
        System.out.println(mensajeDecodificado);
    }
    
    /**
     * Retorna el mensaje ya decodificado/descifrado.
     * Precondición: el mensaje debe haber sido decodificado previamente (i.e., 
     * se debe haber llamado a decodificarMensaje()).
     * Postcondicion: se retorna el mensaje descifrado/decodificado.
     * @return el mensaje descifrado.
     */
    public Mensaje obtenerMensajeDecodificado() {
        if (mensajeDecodificado == null)
            throw new IllegalStateException("Mensaje aún no decodificado");
        return mensajeDecodificado;
    }
    
    /**
     * Desencripta una cadena, dado un código numérico. Se usan los dígitos del código
     * para reemplazar cada caracter de la cadena por el caracter correspondiente a 
     * "trasladar" el mismo el número de lugares que indica el código, en sentido inverso
     * al de encripción (es decir, se resta el código al caracter). El código tiene
     * múltiples valores: se usa el primero para el primer caracter, el segundo para el 
     * segundo, y así sucesivamente. Si se agota el código, se vuelve al comienzo del mismo, 
     * hasta desencriptar toda la cadena.
     * Precondición: tanto str como codigo no deben ser nulos.
     * @param str es la cadena a desencriptar
     * @param codigo es el código a utilizar para la desencripción
     */
    public String desencriptarCadena(String str, int[] codigo) {
        String cadena = mensajeADecodificar.toString();
        //index para recorrer la posicion en el arreglo
        //i para recorrer la posicion en la cadena
        int index = 0;
        //arreglo para guardar el mensaje desencriptado
        char[] nuevoMensaje = new char[cadena.length()];
            
        for(int i = 0; i < cadena.length(); i++){
            // conseguir el ascii de cada caracter en la cadena
            int numAscii = (int) cadena.charAt(i);
            // variable para guardar el tamaño del arreglo
            int tamañoArreglo = codigoEncripcion.length;
            System.out.println(tamañoArreglo);
            System.out.println(cadena.length());
            // condicional para movernos dentro de cada posicion del arreglo una y otra vez
            if(index >= 0 && index < tamañoArreglo){
                // condicional para manejarnos dentro del rango 0-127 en ascii
                if(numAscii - codigoEncripcion[index] < 0){
                    numAscii += 128;
                }
                // desencriptar el carácter restando el valor del código de encriptación
                int k = numAscii - codigoEncripcion[index];
                // almacenar el carácter desencriptado en el nuevo mensaje
                nuevoMensaje[i] = (char) k;
                // incrementar el índice
                index++;
            }
            // reiniciar el índice si se llega al final del arreglo de encriptación
            if(index >= tamañoArreglo){
                index = 0;
            }
        }
           
        // convertir el arreglo de caracteres a una cadena y imprimir el mensaje desencriptado
        String mensajeDecodificado = new String(nuevoMensaje);
        System.out.println(mensajeDecodificado);
        return mensajeDecodificado;
    }
    
    private String desencriptarCadenaV2(String str, int[] codigo){
       
    // index para recorrer la posicion en el arreglo
    // i para recorrer la posicion en la cadena
    int index = 0;
    // arreglo para guardar el mensaje desencriptado
    char[] nuevoMensaje = new char[str.length()];
        
    for(int i = 0; i < str.length(); i++){
        // conseguir el ascii de cada caracter en la cadena
        int numAscii = (int) str.charAt(i);
        // variable para guardar el tamaño del arreglo
        int tamañoArreglo = codigo.length;

        // condicional para movernos dentro de cada posicion del arreglo una y otra vez
        if(index >= 0 && index < tamañoArreglo){
            // condicional para manejarnos dentro del rango 0-127 en ascii
            if(numAscii - codigo[index] < 0){
                numAscii += 128;
            }
            // desencriptar el carácter restando el valor del código de encriptación
            int k = numAscii - codigo[index];
            // almacenar el carácter desencriptado en el nuevo mensaje
            nuevoMensaje[i] = (char) k;
            // incrementar el índice
            index++;
        }
        // reiniciar el índice si se llega al final del arreglo de encriptación
        if(index >= tamañoArreglo){
            index = 0;
        }
    }
       
    // convertir el arreglo de caracteres a una cadena y devolver el mensaje desencriptado
    String mensajeDecodificado = new String(nuevoMensaje);
    System.out.println(nuevoMensaje);
    return mensajeDecodificado;
    }

}

