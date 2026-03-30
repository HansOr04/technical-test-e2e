# technical-test-e2e

> End-to-end test automation project built with **Serenity BDD**, **Cucumber** and the **Screenplay Pattern** in **Java 17** using **Gradle**.

---

## 📋 Table of Contents

- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Running the Tests](#running-the-tests)
- [Viewing Reports](#viewing-reports)
- [Writing New Tests](#writing-new-tests)
- [Tagging Strategy](#tagging-strategy)
- [CI/CD Integration](#cicd-integration)

---

## Prerequisites

| Tool | Version |
|------|---------|
| Java | 17+ |
| Gradle | 8.x (wrapper included) |
| Chrome | Latest stable |
| Git | Any recent version |

> **Note:** ChromeDriver is managed automatically by [WebDriverManager](https://github.com/bonigarcia/webdrivermanager). No manual driver installation required.

---

## Project Structure

```
technical-test-e2e/
├── build.gradle                        # Build configuration & dependencies
├── settings.gradle
├── .gitignore
├── README.md
└── src/
    └── test/
        ├── java/
        │   └── com/technicaltest/e2e/
        │       ├── runners/            # JUnit test runners
        │       │   └── TestRunner.java
        │       ├── steps/              # Cucumber step definitions
        │       │   └── ExampleStepDefinitions.java
        │       ├── tasks/              # Screenplay Tasks (business actions)
        │       │   ├── NavigateTo.java
        │       │   └── Login.java
        │       ├── pages/              # UI Maps → Screenplay Targets
        │       │   ├── LoginPage.java
        │       │   └── HomePage.java
        │       └── models/             # Test data models (POJOs)
        │           └── User.java
        └── resources/
            ├── serenity.conf           # Serenity + WebDriver config
            └── features/              # Gherkin feature files
                └── example/
                    └── example.feature
```

---

## Configuration

### `serenity.conf`

Located at `src/test/resources/serenity.conf`. Key settings:

| Property | Default | Description |
|---|---|---|
| `webdriver.driver` | `chrome` | Browser to use |
| `environments.default.baseUrl` | `https://example.com` | Application under test URL |
| headless | `true` (via args) | Runs Chrome without a UI |

To change the target environment at runtime:
```bash
./gradlew test -Denvironment=staging
```

---

## Running the Tests

### All tests (headless Chrome)
```bash
./gradlew test
```

### Specific tag(s)
```bash
./gradlew test -Dcucumber.filter.tags="@smoke"
```

### Headed mode (with browser visible)
```bash
./gradlew test -Dheadless=false
```

### Against a different URL
```bash
./gradlew test -DbaseUrl=https://staging.example.com
```

---

## Viewing Reports

After a test run, Serenity generates a full HTML report:

```
target/site/serenity/index.html
```

Open it directly in any browser:
```bash
# Windows
start target\site\serenity\index.html

# macOS / Linux
open target/site/serenity/index.html
```

---

## Writing New Tests

### 1. Create a Feature File
Add a `.feature` file under `src/test/resources/features/<module>/`:

```gherkin
Feature: User Login
  @smoke
  Scenario: Successful login with valid credentials
    Given the user navigates to the login page
    When the user logs in with valid credentials
    Then the dashboard should be displayed
```

### 2. Add Step Definitions
Create a class in `src/test/java/com/technicaltest/e2e/steps/`:

```java
@Given("the user navigates to the login page")
public void navigatesToLoginPage() {
    user.attemptsTo(NavigateTo.thePage("/login"));
}
```

### 3. Create Tasks (business actions)
Add domain-level Tasks in `src/test/java/com/technicaltest/e2e/tasks/`.

### 4. Declare Targets (selectors)
Add page locators in `src/test/java/com/technicaltest/e2e/pages/`.

---

## Tagging Strategy

| Tag | Purpose |
|-----|---------|
| `@smoke` | Critical happy-path tests |
| `@regression` | Full regression suite |
| `@wip` | Work-in-progress (excluded by default) |
| `@ignore` | Permanently skipped |

---

## CI/CD Integration

### GitHub Actions (example)

```yaml
- name: Run E2E Tests
  run: ./gradlew test
  env:
    headless: true
```

Reports can be archived as artifacts:
```yaml
- uses: actions/upload-artifact@v4
  if: always()
  with:
    name: serenity-report
    path: target/site/serenity/
```

---

## 📝 TODO

- [ ] Add real application URL to `serenity.conf`
- [ ] Implement login feature scenarios
- [ ] Configure CI/CD pipeline
- [ ] Add data-driven test examples with Cucumber Scenario Outline
- [ ] Integrate with test management tool (e.g., Jira Xray)
