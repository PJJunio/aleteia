# 🦉 Aleteia - Assistente Diário de Inteligência

![Logo Aleteia](aleteialogo.png)

**Aleteia** é um bot assistente desenvolvido em **Java 25** com **Spring Boot 4**, projetado para curar, resumir e entregar inteligência de mercado e notícias diretamente no seu Telegram. Utilizando a poderosa API **Google Gemini 2.0 Flash / 1.5 Flash**, ela transforma grandes volumes de informação em insights acionáveis.

## 🚀 Funcionalidades

A Aleteia opera em três turnos estratégicos:

*   **☀️ Manhã (07h): O Despertar**
    *   Relatório denso do fechamento de mercado anterior.
    *   Análise de Dividendos e Setores (Financeiro, Elétrico).
    *   Destaques do Mundo Dev e Política.
*   **☕ Tarde (12h): Giro do Almoço**
    *   Atualizações rápidas do pregão e volume de blue chips.
    *   Novidades de Tech & Inovação para ler no almoço.
*   **🌙 Noite (19h): Balanço de Fechamento**
    *   Consolidação do dia financeiro (altas e baixas).
    *   Reflexão técnica/acadêmica do dia.

## 🛠️ Tecnologias

*   **Linguagem:** Java 25 (OpenJDK)
*   **Framework:** Spring Boot 4.0.2 (WebFlux - Reativo)
*   **IA:** Google Gemini SDK (v1.36.0) - Modelo `gemini-flash-latest`
*   **Mensageria:** Telegram Bot API
*   **Agendamento:** Spring `@Scheduled`

## ⚙️ Configuração

Para rodar o projeto, você precisa configurar as variáveis de ambiente no arquivo `src/main/resources/application.properties`:

```properties
# Nome da Aplicação
spring.application.name=aleteia

# Google Gemini API Token
# Obtenha em: https://aistudio.google.com/
GEMINI_API_TOKEN=seu_token_aqui

# Telegram Bot Token
# Obtenha com o @BotFather
TELEGRAM_BOT_TOKEN=seu_token_telegram

# ID do Chat (Grupo ou Pessoal)
# Descubra enviando uma mensagem para o bot e acessando:
# https://api.telegram.org/bot<TOKEN>/getUpdates
TELEGRAM_CHAT_ID=-123456789
```

## 📦 Como Executar

Com o Maven instalado:

```bash
./mvnw spring-boot:run
```

Ou execute a classe principal `AleteiaApplication.java` na sua IDE favorita (IntelliJ IDEA recomendado).

---
