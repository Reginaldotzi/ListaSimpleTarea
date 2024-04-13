package edu.umg;

import java.util.Comparator;

public class ListaReproduccion {
    private Nodo inicio; // Puntero al primer nodo de la lista

    // Constructor de la lista de reproducción
    public ListaReproduccion() {
        inicio = null; // La lista comienza vacía
    }

    // Método para verificar si la lista está vacía
    public boolean estaVacia() {
        return inicio == null;
    }

    // Método para insertar una nueva canción al final de la lista
    public void insertarAlFinal(String nombre, String artista, String genero, int duracion) {
        Nodo nuevoNodo = new Nodo(nombre, artista, genero, duracion);
        if (estaVacia()) {
            inicio = nuevoNodo; // Si la lista está vacía, el nuevo nodo se convierte en el primero
        } else {
            Nodo actual = inicio;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente(); // Avanzar al último nodo
            }
            actual.setSiguiente(nuevoNodo); // Enlazar el nuevo nodo al último
        }
    }

    // Método para imprimir la lista completa en orden de inserción
    public void imprimirLista() {
        Nodo actual = inicio;
        while (actual != null) {
            System.out.println("Nombre: " + actual.getNombre() + ", Artista: " + actual.getArtista() +
                    ", Género: " + actual.getGenero() + ", Duración: " + actual.getDuracion() + " segundos");
            actual = actual.getSiguiente();
        }
    }


    // Método para eliminar una canción por su nombre
    public void eliminarPorNombre(String nombre) {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        if (inicio.getNombre().equals(nombre)) {
            inicio = inicio.getSiguiente();
            return;
        }
        Nodo anterior = inicio;
        Nodo actual = inicio.getSiguiente();
        while (actual != null && !actual.getNombre().equals(nombre)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        if (actual == null) {
            System.out.println("La canción '" + nombre + "' no se encontró en la lista.");
        } else {
            anterior.setSiguiente(actual.getSiguiente());
        }
    }

    // Método para insertar una nueva canción en una posición específica
    public void insertarEnPosicion(String nombre, String artista, String genero, int duracion, int posicion) {
        Nodo nuevoNodo = new Nodo(nombre, artista, genero, duracion);
        if (posicion <= 0) {
            System.out.println("La posición debe ser mayor que cero.");
            return;
        }
        if (posicion == 1) {
            nuevoNodo.setSiguiente(inicio);
            inicio = nuevoNodo;
            return;
        }
        Nodo anterior = inicio;
        for (int i = 1; i < posicion - 1 && anterior != null; i++) {
            anterior = anterior.getSiguiente();
        }
        if (anterior == null) {
            System.out.println("La posición especificada está fuera de rango.");
        } else {
            nuevoNodo.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente(nuevoNodo);
        }
    }

    // Método para buscar una canción por su nombre y mostrar su información
    public void buscarPorNombre(String nombre) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Nombre: " + actual.getNombre() + ", Artista: " + actual.getArtista() +
                        ", Género: " + actual.getGenero() + ", Duración: " + actual.getDuracion() + " segundos");
                return; // Terminar la búsqueda una vez que se encuentra la canción
            }
            actual = actual.getSiguiente();
        }
        System.out.println("La canción '" + nombre + "' no se encontró en la lista.");
    }


    // Método para ordenar la playlist por nombre de canción
    public void ordenarPorNombre() {
        inicio = ordenar(inicio, Comparator.comparing(Nodo::getNombre));
    }

    // Método para ordenar la playlist por artista
    public void ordenarPorArtista() {
        inicio = ordenar(inicio, Comparator.comparing(Nodo::getArtista));
    }

    // Método para ordenar la playlist por género
    public void ordenarPorGenero() {
        inicio = ordenar(inicio, Comparator.comparing(Nodo::getGenero));
    }

    // Método para calcular la duración total de la playlist en minutos y segundos
    public void calcularDuracionTotal() {
        int duracionTotal = 0;
        Nodo actual = inicio;
        while (actual != null) {
            duracionTotal += actual.getDuracion();
            actual = actual.getSiguiente();
        }
        int minutos = duracionTotal / 60;
        int segundos = duracionTotal % 60;
        System.out.println("Duración total de la playlist: " + minutos + " minutos y " + segundos + " segundos");
    }

    // Método auxiliar para ordenar la lista utilizando el algoritmo de ordenamiento QuickSort
    private Nodo ordenar(Nodo inicioLista, Comparator<Nodo> comparador) {
        if (inicioLista == null || inicioLista.getSiguiente() == null) {
            return inicioLista; // La lista está vacía o tiene solo un elemento, ya está ordenada
        }

        // Dividir la lista en dos partes
        Nodo pivote = inicioLista;
        Nodo menor = null;
        Nodo mayor = inicioLista.getSiguiente();

        while (mayor != null) {
            if (comparador.compare(mayor, pivote) < 0) {
                // Mover el nodo 'mayor' al principio de la lista
                pivote.setSiguiente(mayor.getSiguiente());
                mayor.setSiguiente(inicioLista);
                inicioLista = mayor;
                menor = mayor;
                mayor = pivote.getSiguiente();
            } else {
                // Avanzar al siguiente nodo
                pivote = mayor;
                mayor = mayor.getSiguiente();
            }
        }

        // Ordenar las sublistas recursivamente
        if (menor != null) {
            menor.setSiguiente(ordenar(menor.getSiguiente(), comparador));
        }
        return inicioLista;
    }
}

