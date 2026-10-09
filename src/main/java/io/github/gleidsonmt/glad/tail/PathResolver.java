package io.github.gleidsonmt.glad.tail;

import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 06/10/2026
 */
public class PathResolver {

    private final Path ROOT_DIR = Paths.get(System.getProperty("user.dir"));
    private final Path SOURCE_DIR = ROOT_DIR.resolve("src/main/java");

    // Deixamos o CSS_OUTPUT dinâmico para ser resolvido na inicialização
    private Path cssOutputPath;

    private final Logger log = Logger.getGlobal();


    // Método mágico que varre a sua pasta src/main/java, descobre o pacote atual
    // e espelha a mesma estrutura dentro de resources/
    public void resolve() {
        try {
            // Procura o primeiro arquivo .java para descobrir qual é o pacote do projeto (ex: todo)
            Path firstJavaFile = Files.walk(SOURCE_DIR)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".java"))
                    .findFirst()
                    .orElse(null);

            String packagePathStr = "css/tailwind.css"; // Fallback padrão caso não ache

            if (firstJavaFile != null) {
                // Pega o caminho relativo a partir de src/main/java (ex: io/github/gleidsonmt/todo/Main.java)
                Path relativePath = SOURCE_DIR.relativize(firstJavaFile);
                Path packageFolder = relativePath.getParent(); // Retorna: io/github/gleidsonmt/todo

                if (packageFolder != null) {
                    // Monta o caminho final jogando a pasta 'css' dentro do pacote descoberto
                    packagePathStr = packageFolder.toString().replace("\\", "/") + "/css/tailwind.css";
                }
            }

            // Define o caminho de saída apontando para a pasta resources do projeto que está rodando
            cssOutputPath = ROOT_DIR.resolve("src/main/resources").resolve(packagePathStr);
//            System.out.println("🎯 [JIT Config] Destino mapeado para: " + cssOutputPath);
            log.info("🎯 [JIT Config] Destino mapeado para: " + cssOutputPath);

        } catch (IOException e) {
            // Se falhar por algum motivo, joga na raiz de resources/css como segurança
            cssOutputPath = ROOT_DIR.resolve("src/main/resources/css/typographic.css");
        }
    }

    public void write(ObservableList<String> classes) {
        StringBuilder cssContent = new StringBuilder();

        for (String cls : classes) {
            var temp = new ClassConverter().convert(cls);
            if (temp != null) {
                cssContent.append(temp).append(" ");
            }
        }
        if (!cssContent.isEmpty()) {
            store(cssContent.toString(),  StandardOpenOption.TRUNCATE_EXISTING);
        }
    }


    public String append(String className) {
        var temp = className;
        try {
            String linhasAtuais = Files.readString(cssOutputPath);
//            System.out.println("temp = " + temp);
            if (temp != null && !linhasAtuais.contains(temp)) {
                store(temp, StandardOpenOption.APPEND);
//                System.out.println("linhasAtuais.contains(temp) = " + linhasAtuais.contains(temp));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return className;
    }

    public void store(String content, StandardOpenOption... operation) {
        try {

            Path parentDir = cssOutputPath.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
                System.out.println("📁 [JIT Setup] Estrutura de pacotes criada em: " + parentDir);
            }

            if (!Files.exists(cssOutputPath)) {
                Files.createFile(cssOutputPath);
            }

            // 2. Se as pastas do pacote não existirem na árvore, cria todas de uma vez
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
                System.out.println("📁 [JIT Setup] Diretórios criados automaticamente: " + parentDir);
            }

            Files.write(cssOutputPath, content.getBytes(), operation);
        } catch (IOException e) {
            System.err.println("❌ Erro crítico ao compilar diretórios: " + e.getMessage());
        }
    }
}
