### Java Spring Framework && ThymeLife Fundamentos
*** Sucesso Finalizado ****
* Reference:
```Java TreinaWeb
www.treinaweb.com.br/painel/cursos/spring-framework-fundamentos/aula/5/subaula/4
```

#### H2 Configuration
* src/main/resources application.properties

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
```

* Acessando no navegador
```
http://localhost:8080/h2-console/
```

```JDBC URL:
jdbc:h2:mem:testdb
```

#### Mysl properties
* src/main/resources application.properties

```
spring.datasource.url=jdbc:mysql://localhost:3306/api_rest2
spring.datasource.username=root
spring.datasource.password=

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
```

#### Estrutra Base Projeto Spring Api + ThymeLife

[] Properties banco

[] Models
    [] Opcional Records ou DTOs
[] Repositorys
[] Controllers

#### [] Modelo de uma Entity Java com Lombook
```
@Data
@Entity
@Table(name = "produtos2")
public class Produto2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( unique = true, nullable = false, length = 50)
    private String name;

    @Column( nullable = false)
    private BigDecimal preco;

    @Column( nullable = false)
    private Integer quantidade;
    
}
```

#### [] Modelo de uma Entity Java sem Lombook
```
@Entity
@Table(name = "produtos")
public class Produto {

    // 1. Colunas da tabela a ser gerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer quantidade;

    // 2. construtor sem parametros
    public Produto() {}

    // 3. construtor com parametros
    public Produto(Long id, String nome, BigDecimal preco, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

     // 4. toString
     @Override
     public String toString() {
         return "Produto: id=" + id + ", nome=" + nome + ", preco=" + preco + ", quantidade=" + quantidade + " ";
     }

    // 5. getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public Integer getQuantidade() { return quantidade; }

    // 6. setters
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    // 7. hashCode methods
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    // 8. equals  (verifica se objetos são iguais)  -- Utiliza o id para comparar se objetos são iguais
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
```

#### [] Modelo Repository
```
public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
```

#### [] Modelo Controllers
``` /** Opção 1 Campo telefone formatado */
@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClienteController {

    private final ClientRepository clientRepository;

    @GetMapping
    public ModelAndView index() {
        /** Opção 1 */
        var clients = clientRepository.findAll();
        var clientsViewModel = new ArrayList<ClientViewModel>();
        for (Client client : clients) {
            var clientViewModel = ClientViewModel.of(client);
            clientsViewModel.add(clientViewModel);
        }
        var model = Map.of("clients", clientsViewModel);
        return new ModelAndView("clients/index", model);

    }
}
```

``` /** Opção 2 Campo telefone formatado */
@SuppressWarnings("unused")
@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClienteController {
    
    private final ClientRepository clientRepository;

    @GetMapping
    public ModelAndView index(){
    /*** Opção 2  */
        var clients = clientRepository.findAll()
        .stream()
        .map(ClientViewModel::of)
        .toList();
    
        var model = Map.of("clients", clients);
        return new ModelAndView("clients/index", model);
    }
}
```

#### Modelo Services

#### Modelo DTO SHOW +D
```
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientViewModel {
    
    private Long id;
    private String name;
    private String phone;
    private String email;

    public String getPhone(){
        // return phone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
        return phone.replaceAll("(\\d{2})(\\d{1})(\\d{4})(\\d{4})", "($1) $2 $3-$4");
    }

    public static ClientViewModel of(Client client){
        return new ClientViewModel(
            client.getId(),
            client.getName(),
            client.getPhone(),
            client.getEmail()
        );
    }
}
```

#### Modelo Record


