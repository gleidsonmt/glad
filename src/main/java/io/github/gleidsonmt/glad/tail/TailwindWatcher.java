package io.github.gleidsonmt.glad.tail;

import javafx.collections.ObservableList;

import java.io.IOException;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 28/09/2026
 */
public class TailwindWatcher {

    private PathResolver path = new PathResolver();

//    private static final Logger log = Logger.getGlobal();
//
//    // Método mágico que varre a sua pasta src/main/java, descobre o pacote atual
//    // e espelha a mesma estrutura dentro de resources/
//    private void resolveDynamicPaths() {
//        try {
//            // Procura o primeiro arquivo .java para descobrir qual é o pacote do projeto (ex: todo)
//            Path firstJavaFile = Files.walk(SOURCE_DIR)
//                    .filter(Files::isRegularFile)
//                    .filter(p -> p.toString().endsWith(".java"))
//                    .findFirst()
//                    .orElse(null);
//
//            String packagePathStr = "css/tailwind.css"; // Fallback padrão caso não ache
//
//            if (firstJavaFile != null) {
//                // Pega o caminho relativo a partir de src/main/java (ex: io/github/gleidsonmt/todo/Main.java)
//                Path relativePath = SOURCE_DIR.relativize(firstJavaFile);
//                Path packageFolder = relativePath.getParent(); // Retorna: io/github/gleidsonmt/todo
//
//                if (packageFolder != null) {
//                    // Monta o caminho final jogando a pasta 'css' dentro do pacote descoberto
//                    packagePathStr = packageFolder.toString().replace("\\", "/") + "/css/tailwind.css";
//                }
//            }
//
//            // Define o caminho de saída apontando para a pasta resources do projeto que está rodando
//            cssOutputPath = ROOT_DIR.resolve("src/main/resources").resolve(packagePathStr);

    /// /            System.out.println("🎯 [JIT Config] Destino mapeado para: " + cssOutputPath);
//            log.info("🎯 [JIT Config] Destino mapeado para: " + cssOutputPath);
//
//        } catch (IOException e) {
//            // Se falhar por algum motivo, joga na raiz de resources/css como segurança
//            cssOutputPath = ROOT_DIR.resolve("src/main/resources/css/typographic.css");
//        }
//    }
    public void printRules() {
//        String[] list = acceptablePrefixes.split("|");
//        for (String prefix : list) {
//            System.out.println("Prefix: " + prefix);
//        }

    }

    public void start() throws IOException, InterruptedException {
        path.resolve();

//        resolveDynamicPaths();
        // 1. Executa uma compilação inicial ao abrir
//        compileAllFiles();

        // 2. Configura o serviço de monitoramento do sistema operacional
//        WatchService watchService = FileSystems.getDefault().newWatchService();
//
//        // Registra a pasta src e todas as subpastas recursivamente
//        registerAllDirectories(watchService);
//
////        System.out.println("👀 [JIT Tailwind] Monitorando alterações na pasta 'src/'...");
//        log.info("👀 [JIT Tailwind] Monitorando alterações na pasta 'src/'...");
//
//        while (true) {
//            WatchKey key = watchService.take(); // Espera por uma alteração no disco
//            boolean shouldCompile = false;
//
//            for (WatchEvent<?> event : key.pollEvents()) {
//                Path changedFile = (Path) event.context();
//                // Só dispara a compilação se o arquivo alterado for um código Java
//                if (changedFile.toString().endsWith(".java")) {
//                    shouldCompile = true;
//                }
//            }
//
//            if (shouldCompile) {
//                long start = System.currentTimeMillis();
//                compileAllFiles();
//                long duration = System.currentTimeMillis() - start;
////                System.out.println("⚡ CSS atualizado de forma JIT em " + duration + "ms!");
//                log.info("⚡ CSS atualizado de forma JIT em " + duration + "ms!");
//            }
//
//            boolean valid = key.reset();
//            if (!valid) break; // Sai se o diretório sumir
//        }
    }

    // Varre recursivamente todas as subpastas do seu projeto
//    private static void registerAllDirectories(final WatchService watchService) throws IOException {
//        Files.walkFileTree(TailwindWatcher.SOURCE_DIR, new SimpleFileVisitor<>() {
//            @Override
//            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//                dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
//                return FileVisitResult.CONTINUE;
//            }
//        });
//    }

    public void append(String clazz) {
        path.append(new ClassConverter().convert(clazz));
    }

    public void write(ObservableList<String> classes) {
        path.write(classes);
    }
}


