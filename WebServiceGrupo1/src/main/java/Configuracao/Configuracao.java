
package Configuracao;

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
        
//        resources.add(CategoriaResource.CategoriaResource.class);
        resources.add(Resources.CategoriaResource.class);
        resources.add(Resources.DetalheResource.class);
//        resources.add(fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);
//        resources.add(fasterxml.jackson.jaxrs.json.JacksonJsonProvider.class);
        resources.add(Resources.ProdutosResources.class);
       // resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
        resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);

        
        
    }
    
}
