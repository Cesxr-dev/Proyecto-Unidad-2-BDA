
package presentacion;

import dominio.Carrera;
import dominio.InfoAdicional;
import dominio.Perfil;
import dominio.TipoInfo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import servicios.IInfoAdicionalService;
import servicios.IPerfilService;
import servicios.InfoAdicionalService;
import servicios.PerfilService;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public class CampusConnectMain {

    public static void main(String[] args) {
        IInfoAdicionalService infoService = new InfoAdicionalService();
        IPerfilService perfilService = new PerfilService();
        
        InfoAdicional info1 = new InfoAdicional();
        info1.setTipo(TipoInfo.GUSTO);
        info1.setNombre("Ceviche");
        
        Perfil perfil = new Perfil();
        perfil.setNombre("Pablo");
        perfil.setFechaNacimiento(LocalDate.parse("2003-12-09"));
        perfil.setCarrera(Carrera.ARQUITECTURA);
        perfil.setCorreoInstitucional("pablo.apellid123456@potros.itson.edu.mx");
        perfil.setContrasena("contra1234");
        
        Set<InfoAdicional> info1Set = new HashSet<>();
        info1Set.add(info1);
        Set<Perfil> info1PerfilesSet = new HashSet<>();
        info1PerfilesSet.add(perfil);
        
        perfil.setPerfilInfoAdicional(info1Set);
        info1.setPerfiles(info1PerfilesSet);
        
        // Creo que guardar de esta manera tanto info adicional como perfil está mal
        // quizás deberían guardarse ambos primero sin la información del otro 
        // (pefil sin info adicional e info adicional sin perfiles), y después ya 
        // creados, actualizarse para ahora si estar relacionados, todo durante 
        // la creación de perfil, por ahora lo dejo así porque todavia no está el
        // método de actualizar hecho
        infoService.guardar(info1);
        perfilService.guardar(perfil);
    }
    
}
