import java.util.Objects;

public class Profesor {

    private String nombre;
    private String apellido;
    private String email;
    private int dni;
    private String materia;

    public Profesor() {
    }

    /*
     * El constructor de la clase Profesor recibe como parámetros los atributos de la clase Profesor
     *  y los asigna a los atributos de la misma.
     * */
    public Profesor(String nombre, String apellido, String email, int dni, String materia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.materia = materia;
    }

    /*
     * El método getNombre retorna el valor del atributo nombre de la clase Profesor.
     * */
    public String getNombre() {
        return nombre;
    }

    /*
     * El método setNombre recibe como parámetro el valor del atributo nombre de la clase Profesor.
     * */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
     * El método getApellido retorna el valor del atributo apellido de la clase Profesor.
     * */
    public String getApellido() {
        return apellido;
    }

    /*
     * * El método setApellido recibe como parámetro el valor del atributo apellido de la clase Profesor.
     * */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /*
     * El método getEmail retorna el valor del atributo email de la clase Profesor.
     * */
    public String getEmail() {
        return email;
    }

    /*
     * El método setEmail recibe como parámetro el valor del atributo email de la clase Profesor.
     * */
    public void setEmail(String email) {
        this.email = email;
    }

    /*
     * El método getDni retorna el valor del atributo dni de la clase Profesor.
     * */
    public int getDni() {
        return dni;
    }

    /*
     * * El método setDni recibe como parámetro el valor del atributo dni de la clase Profesor.
     * */
    public void setDni(int dni) {
        this.dni = dni;
    }

    /*
     * El método getMateria retorna el valor del atributo materia de la clase Profesor.
     * */
    public String getMateria() {
        return materia;
    }

    /*
     ** El método setMateria recibe como parámetro el valor del atributo materia de la clase Profesor.
     * */
    public void setMateria(String materia) {
        this.materia = materia;
    }

    /*
     * El método equals compara dos objetos de la clase Profesor segón sus atributos.
     * Si los atributos son iguales, retorna true.
     * Si los atributos son distintos, retorna false.
     * El método equals debe ser sobreescrito para que funcione correctamente con la clase ArrayList.
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesor)) return false;
        Profesor profesor = (Profesor) o;
        return dni == profesor.dni && Objects.equals(nombre, profesor.nombre) && Objects.equals(apellido, profesor.apellido) && Objects.equals(email, profesor.email) && Objects.equals(materia, profesor.materia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, email, dni, materia);
    }

    /*
     * * El método toString retorna una cadena de caracteres que representa al objeto Profesor.
     * * El método toString debe ser sobreescrito para que funcione correctamente con la clase ArrayList.
     * * El método toString debe retornar los atributos separados por comas.
     * */
    @Override
    public String toString() {
        return
                nombre + ',' + apellido + ',' + email + ',' + dni + ',' + materia;
    }
}
