E-commerce Automation Framework | Selenium & Java
- Descriere Proiect
Acest proiect reprezintă un cadru de testare automatizată End-to-End (E2E), construit de la zero pentru a valida fluxurile critice ale unei platforme de e-commerce (SauceDemo). Obiectivul principal a fost implementarea unei arhitecturi scalabile și ușor de întreținut, utilizând bunele practici din industrie.

- Arhitectură: Page Object Model (POM)
Cadrul utilizează design pattern-ul Page Object Model, asigurând o separare completă între logica testelor și elementele UI (locatori).

Base: Conține BaseTest, clasa părinte care gestionează ciclul de viață al driverului (Setup/Teardown).

Pages: Clase dedicate pentru fiecare pagină (Login, Products, Cart, Checkout), încapsulând selectorii și metodele de interacțiune.

Tests: Scenarii de testare organizate logic pe module funcționale.

- Stack Tehnic
Limbaj: Java (JDK 23)

Automatizare UI: Selenium WebDriver (v4.16.1)

Test Framework: JUnit 5 (Jupiter)

Dependency Management: Maven

Driver Management: WebDriverManager (v5.6.0) pentru gestionare automată cross-browser

IDE: IntelliJ IDEA

- Scenarii de Testare Automatizate

Cadrul acoperă următoarele fluxuri critice:

Authentication: Login cu credențiale valide, gestionarea erorilor pentru parole invalide și validarea formatului de email.

Product Management: Verificarea afișării produselor, adăugarea în coș și filtrarea funcțională.

Checkout Process: Flux complet de achiziție, de la vizualizarea coșului până la introducerea datelor de livrare și confirmarea comenzii.

- Configurare și Execuție
Pentru a rula acest proiect local, urmează acești pași:

1) Clonare depozit:

git clone https://github.com/raztot/QA-Portfolio-Project-

2) Instalare dependențe:

mvn clean install

3) Rulare teste:

mvn test

- Caracteristici Cheie
Ciclul de viață al testelor: Utilizarea adnotărilor @BeforeEach și @AfterEach pentru a asigura independența testelor și curățarea resurselor după execuție.

Aserțiuni Robuste: Validarea stărilor aplicației folosind assertTrue și assertEquals pentru a asigura integritatea datelor.

Scalabilitate: Structura permite adăugarea rapidă de noi pagini sau scenarii fără a afecta codul existent.

Autor: Totilca Ionut Razvan   LinkedIn: https://www.linkedin.com/in/ionut-razvan-totilca-ab5973376/
