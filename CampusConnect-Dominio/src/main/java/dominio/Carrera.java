
package dominio;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public enum Carrera {
    // Licenciaturas
    ADMINISTRACION("Administración"),
    ADMINISTRACION_EMPRESAS_TURISTICAS("Administración de Empresas Turísticas"),
    ARQUITECTURA("Arquitectura"),
    CIENCIAS_EDUCACION("Ciencias de la Educación"),
    CIENCIAS_EJERCICIO_FISICO("Ciencias del Ejercicio Físico"),
    CONTADURIA_PUBLICA("Contaduría Pública"),
    DIRECCION_CULTURA_FISICA_DEPORTE("Dirección de la Cultura Física y el Deporte (Virtual)"),
    DISENO_GRAFICO("Diseño Gráfico"),
    ECONOMIA_FINANZAS("Economía y Finanzas"),
    EDUCACION_INFANTIL("Educación Infantil"),
    EDUCACION_INICIAL_GESTION("Educación Inicial y Gestión de Instituciones"),
    EMPRENDIMIENTO_INNOVACION("Emprendimiento e Innovación (Virtual)"),
    GASTRONOMIA("Gastronomía"),
    GESTION_DESARROLLO_ARTES("Gestión y Desarrollo de las Artes"),
    MERCADOTECNIA("Mercadotecnia"),
    PSICOLOGIA("Psicología"),
    TECNOLOGIA_ALIMENTOS("Tecnología de Alimentos"),
    MEDICO_VETERINARIO_ZOOTECNISTA("Médico Veterinario Zootecnista"),

    // Ingenierías
    INGENIERIA_BIOSISTEMAS("Ingeniería en Biosistemas"),
    INGENIERIA_BIOTECNOLOGIA("Ingeniería en Biotecnología"),
    INGENIERIA_CIENCIAS_AMBIENTALES("Ingeniería en Ciencias Ambientales"),
    INGENIERIA_CIVIL("Ingeniería Civil"),
    INGENIERIA_ELECTROMECANICA("Ingeniería Electromecánica"),
    INGENIERIA_ELECTRONICA("Ingeniería en Electrónica"),
    INGENIERIA_INDUSTRIAL_SISTEMAS("Ingeniería Industrial y de Sistemas"),
    INGENIERIA_MANUFACTURA("Ingeniería en Manufactura"),
    INGENIERIA_MECATRONICA("Ingeniería en Mecatrónica"),
    INGENIERIA_QUIMICA("Ingeniería Química"),
    INGENIERIA_SOFTWARE("Ingeniería en Software");
    
    private final String nombre;

    Carrera(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
