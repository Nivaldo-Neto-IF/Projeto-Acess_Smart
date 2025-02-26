package acesssmart;
interface ResourceQuery {
    void buscar();
  }
  
  class BasicResourceQuery implements ResourceQuery {
    public void buscar() {
        System.out.println("Buscando todos os recursos...");
    }
  }
  
  class FilteredResourceQuery implements ResourceQuery {
    private ResourceQuery query;
    private String filtro;
  
    public FilteredResourceQuery(ResourceQuery query, String filtro) {
        this.query = query;
        this.filtro = filtro;
    }
  
    public void buscar() {
        query.buscar();
        System.out.println("Aplicando filtro: " + filtro);
    }
  }

