
package Configuracao;

import Resource.LoginResource;
import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")
public class Configuracao extends Application {
    
    @Override
    public Set<Class<?>>getClasses(){
         Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        
        resources.add(Resource.CadastroResource.class);
  //      resources.add(fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);
  //      resources.add(fasterxml.jackson.jaxrs.json.JacksonJsonProvider.class);
        resources.add(Resource.LoginResource.class);
        resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
     
        
    }
    
}
