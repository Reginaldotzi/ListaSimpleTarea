package edu.umg;

import java.util.Scanner;

public class MenuInteractivo {
    private ListaReproduccion lista;

    // Constructor
    public MenuInteractivo() {
        lista = new ListaReproduccion();
    }

    // Método para mostrar el menú y manejar las operaciones
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Agregar canción");
            System.out.println("2. Eliminar canción por nombre");
            System.out.println("3. Insertar canción en posición específica");
            System.out.println("4. Buscar canción por nombre");
            System.out.println("5. Ordenar playlist");
            System.out.println("6. Calcular duración total de la playlist");
            System.out.println("7. Mostrar playlist completa");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente
            switch (opcion) {
                case 1:
                    agregarCancion(scanner);
                    break;
                case 2:
                    eliminarCancion(scanner);
                    break;
                case 3:
                    insertarCancion(scanner);
                    break;
                case 4:
                    buscarCancion(scanner);
                    break;
                case 5:
                    ordenarPlaylist(scanner);
                    break;
                case 6:
                    calcularDuracionTotal();
                    break;
                case 7:
                    mostrarPlaylistCompleta();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    // Método para agregar una canción a la playlist
    private void agregarCancion(Scanner scanner) {
        System.out.println("\n--- Agregar Canción ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Artista: ");
        String artista = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        System.out.print("Duración (segundos): ");
        int duracion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        lista.insertarAlFinal(nombre, artista, genero, duracion);
        System.out.println("Canción agregada exitosamente a la playlist.");
    }

    // Método para eliminar una canción por su nombre
    private void eliminarCancion(Scanner scanner) {
        System.out.println("\n--- Eliminar Canción ---");
        System.out.print("Ingrese el nombre de la canción a eliminar: ");
        String nombre = scanner.nextLine();
        lista.eliminarPorNombre(nombre);
    }

    // Método para insertar una canción en una posición específica
    private void insertarCancion(Scanner scanner) {
        System.out.println("\n--- Insertar Canción ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Artista: ");
        String artista = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        System.out.print("Duración (segundos): ");
        int duracion = scanner.nextInt();
        System.out.print("Posición: ");
        int posicion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        lista.insertarEnPosicion(nombre, artista, genero, duracion, posicion);
        System.out.println("Canción insertada exitosamente en la posición " + posicion + ".");
    }

    // Método para buscar una canción por su nombre
    private void buscarCancion(Scanner scanner) {
        System.out.println("\n--- Buscar Canción ---");
        System.out.print("Ingrese el nombre de la canción a buscar: ");
        String nombre = scanner.nextLine();
        lista.buscarPorNombre(nombre);
    }

    // Método para ordenar la playlist
    private void ordenarPlaylist(Scanner scanner) {
        System.out.println("\n--- Ordenar Playlist ---");
        System.out.println("1. Por nombre de canción");
        System.out.println("2. Por artista");
        System.out.println("3. Por género");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        switch (opcion) {
            case 1:
                lista.ordenarPorNombre();
                System.out.println("Playlist ordenada por nombre de canción.");
                break;
            case 2:
                lista.ordenarPorArtista();
                System.out.println("Playlist ordenada por artista.");
                break;
            case 3:
                lista.ordenarPorGenero();
                System.out.println("Playlist ordenada por género.");
                break;
            default:
                System.out.println("Opción no válida. La playlist no ha sido modificada.");
        }
    }

    // Método para calcular la duración total de la playlist
    private void calcularDuracionTotal() {
        lista.calcularDuracionTotal();
    }

    // Método para mostrar la playlist completa
    private void mostrarPlaylistCompleta() {
        System.out.println("\n--- Playlist Completa ---");
        lista.imprimirLista();
    }
}
