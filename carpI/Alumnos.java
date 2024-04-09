package carpI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Alumnos {
    private String nombre;
    private int id;
    private List<Float> promedio;
    private float promFinal;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public float getPromFinal() {
        return promFinal;
    }

    public void setPromFinal(float promFinal) {
        this.promFinal = promFinal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Float> getPromedio() {
        return promedio;
    }

    public void setPromedio(List<Float> promedio) {
        this.promedio = promedio;
    }

    public static boolean Buscar_usuario_id(Alumnos us) throws IOException {
        String nombre_arch = "alumnos.csv";
        File archivo = new File(nombre_arch);
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean encontrado = false;
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split("\t");
                String id = String.valueOf(us.getId());
                if (id.equals(columnas[0])) {
                    encontrado = true;
                    us.setNombre(columnas[1]);
                    List<Float> promedio = new ArrayList();
                    promedio.add(Float.parseFloat(columnas[2]));
                    promedio.add(Float.parseFloat(columnas[3]));
                    promedio.add(Float.parseFloat(columnas[4]));
                    float suma=0;
                    suma=promedio.get(0);
                    suma= promedio.get(1);
                    suma=promedio.get(2);

                    us.setPromedio(promedio);
                    us.setPromFinal(suma/3);
                    return encontrado;

                }

            }
            if (!encontrado) {
                System.out.println("alumno no encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Error al buscar el alumno en el archivo.");
            e.printStackTrace();
        }
        return false;
    }
}