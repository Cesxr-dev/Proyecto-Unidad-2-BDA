
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
        
        //  Creación de entidad información adicional (se guarda sin lista de perfiles)
        InfoAdicional info1 = new InfoAdicional();
        info1.setTipo(TipoInfo.GUSTO);
        info1.setNombre("Ceviche");
        info1.setPerfiles(new HashSet<>());
        infoService.guardar(info1);
        
        InfoAdicional info2 = new InfoAdicional();
        info2.setTipo(TipoInfo.HOBBIE);
        info2.setNombre("Football");
        info2.setPerfiles(new HashSet<>());
        infoService.guardar(info2);
        
        //  Creación de entidad perfil
        Perfil perfil = new Perfil();
        perfil.setNombre("Pablo");
        perfil.setFechaNacimiento(LocalDate.parse("2003-12-09"));
        perfil.setCarrera(Carrera.ARQUITECTURA);
        perfil.setCorreoInstitucional("pablo.apellid123456@potros.itson.edu.mx");
        perfil.setContrasena("contra1234");
        
        Perfil perfil2 = new Perfil();
        perfil2.setNombre("Regina");
        perfil2.setFechaNacimiento(LocalDate.parse("2002-10-24"));
        perfil2.setCarrera(Carrera.GASTRONOMIA);
        perfil2.setCorreoInstitucional("regina.montec654321@potros.itson.edu.mx");
        perfil2.setContrasena("sena4321");
        
        //  Se guarda en la base de datos
        Set<InfoAdicional> info1Set = new HashSet<>();
        info1Set.add(info1);
        perfil.setPerfilInfoAdicional(info1Set);
        perfilService.guardar(perfil);
        
        Set<InfoAdicional> info2Set = new HashSet<>();
        info2Set.add(info2);
        perfil2.setPerfilInfoAdicional(info2Set);
        perfilService.guardar(perfil2);
        
        //  Se actualiza info adicional para mantener bidireccionalidad
        info1.getPerfiles().add(perfil);
        infoService.actualizar(info1);
        
        info2.getPerfiles().add(perfil2);
        infoService.actualizar(info2);
        
        //  Actualización del perfil 2
        perfil2.setCarrera(Carrera.PSICOLOGIA);
        perfilService.actualizar(perfil2);
    }
    
}
