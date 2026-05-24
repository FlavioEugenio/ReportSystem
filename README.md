<div align="center">

# 🚨 ReportSystem

**Plugin de denúncias avançado para servidores Minecraft Bukkit/Spigot**

![Java](https://img.shields.io/badge/Java-8-orange?style=for-the-badge&logo=java)
![Minecraft](https://img.shields.io/badge/Minecraft-1.8.8-brightgreen?style=for-the-badge&logo=minecraft)
![Maven](https://img.shields.io/badge/Build-Maven-red?style=for-the-badge&logo=apachemaven)
![Spigot](https://img.shields.io/badge/API-Spigot%2FBukkit-yellow?style=for-the-badge)
![IntelliJ IDEA](https://img.shields.io/badge/IDE-IntelliJ%20IDEA-purple?style=for-the-badge&logo=intellijidea)
![Version](https://img.shields.io/badge/Version-1.0-blue?style=for-the-badge)

Sistema completo de reports com interface gráfica, integração com Discord e painel de moderação para servidores Minecraft.

</div>

---

## 📋 Sobre o Projeto

O **ReportSystem** é um plugin desenvolvido em Java para servidores Minecraft Bukkit/Spigot 1.8, com foco em **organização, moderação e gerenciamento de denúncias**. Conta com menus interativos via GUI, sistema de logs, filtros por status/categoria, anti-spam e integração com Discord via Webhook.

---

## ✨ Funcionalidades

### 📢 Sistema de Reports
- Reportar jogadores com motivo personalizado e categoria
- Prevenção de auto-report (não pode reportar a si mesmo)
- Sistema anti-spam com cooldown configurável
- Blacklist de palavras proibidas nos motivos

### 🖥️ Menu GUI Interativo
- Interface gráfica intuitiva com sistema de páginas
- Filtros por **status** (aberto / fechado) e **categoria** (HACK, CHAT, ABUSE)
- Visualização detalhada de cada report
- Ações de resolver e deletar reports diretamente pelo menu

### 🛡️ Painel Staff
- Logs administrativos de todas as ações
- Alteração de status dos reports
- Histórico de ações por moderador
- Sistema de permissões separado por função

### 💬 Integração com Discord
- Envio automático via Webhook
- Notificações em tempo real a cada novo report
- Logs de ações da staff no canal configurado

### 💾 Persistência de Dados
- Salvamento em arquivos YAML (`reports.yml`, `logs.yml`)
- Cache em memória para performance
- Carregamento automático ao iniciar o servidor
- Salvamento assíncrono para não travar o servidor

---

## 🎮 Comandos

| Comando | Descrição | Permissão |
|---|---|---|
| `/report <jogador> <motivo>` | Reportar um jogador | Todos |
| `/reports` | Abrir o painel de reports | `reports.view` |
| `/reportstatus` | Alterar status de um report | `reports.status` |
| `/reportlogs` | Visualizar logs administrativos | `reports.view` |

---

## 🔑 Permissões

| Permissão | Descrição |
|---|---|
| `reports.view` | Ver o painel e lista de reports |
| `reports.delete` | Deletar reports |
| `reports.status` | Alterar o status de reports |

---

## 🗂️ Categorias de Report

| Categoria | Uso |
|---|---|
| `HACK` | Uso de cheats, hacks ou modificações ilegais |
| `CHAT` | Ofensas, spam ou conduta inadequada no chat |
| `ABUSE` | Abuso de mecânicas, exploits ou comportamento tóxico |

---

## ⚙️ Configuração

### `config.yml` — Configuração Geral

```yaml
# Integração com Discord
webhook:
  enabled: false
  url: "COLE_SEU_WEBHOOK_AQUI"

# Cooldown entre reports (em segundos)
cooldown: 60

# Palavras bloqueadas nos motivos
blacklist:
  - "fdp"
  - "lixo"
  - "hackclient"
```

### `plugin.yml` — Registro de Comandos e Permissões

Arquivo gerado automaticamente pelo Maven ao compilar.

---

## 📁 Estrutura do Projeto

```
ReportSystem/
├── src/main/java/com/flavio/reportsystem/
│   ├── commands/       # Handlers dos comandos (/report, /reports...)
│   ├── listeners/      # Eventos do Bukkit (PlayerJoin, etc.)
│   ├── managers/       # Lógica de negócio (ReportManager, LogManager...)
│   ├── models/         # Modelos de dados (Report, LogEntry...)
│   └── utils/          # Utilitários (DiscordWebhook, GUIBuilder...)
├── libs/
│   └── spigot-1.8.8.jar
├── pom.xml
└── README.md
```

### Arquivos de Dados (gerados automaticamente)

| Arquivo | Descrição |
|---|---|
| `reports.yml` | Armazena todos os reports |
| `logs.yml` | Histórico de ações administrativas |
| `config.yml` | Configurações gerais do plugin |
| `plugin.yml` | Registro de comandos e permissões |

---

## 🚀 Como Instalar

### Pré-requisitos

- Java 8+
- Maven 3.6+
- Servidor Spigot/Bukkit 1.8.8

### 1. Clone o repositório

```bash
git clone https://github.com/FlavioEugenio/ReportSystem.git
cd ReportSystem
```

### 2. Compile com Maven

```bash
mvn clean package
```

O `.jar` será gerado em `target/ReportSystem-1.0.jar`.

### 3. Instale no servidor

Copie o arquivo gerado para a pasta `plugins/` do seu servidor:

```bash
cp target/ReportSystem-1.0.jar /caminho/do/servidor/plugins/
```

### 4. Inicie o servidor

O plugin criará os arquivos de configuração automaticamente. Edite o `config.yml` conforme necessário e use `/reload` ou reinicie o servidor.

---

## 🛠️ Tecnologias Utilizadas

### Linguagens & Frameworks
- **Java 8** — Linguagem principal
- **Bukkit / Spigot API 1.8.8** — API do servidor Minecraft
- **Maven** — Gerenciamento de dependências e build
- **YAML** — Persistência de dados
- **Discord Webhook** — Integração externa de notificações

### Ambiente de Desenvolvimento
- **IntelliJ IDEA** — IDE principal, com extensões recomendadas pela própria IDE (suporte a Maven, Minecraft development)
- **Servidor Minecraft local (localhost)** — Ambiente de testes rodando Spigot 1.8.8 na própria máquina

---

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido para consolidar conhecimentos em:

- Programação orientada a objetos em Java
- Desenvolvimento de plugins com a Bukkit API
- Manipulação de inventários (GUI) e eventos do Spigot
- Arquitetura em camadas (commands / listeners / managers / models)
- Persistência de dados com YAML
- Integração com APIs externas (Discord Webhook)
- Salvamento assíncrono para performance

---

## 👨‍💻 Autor

**Flavio Eugenio**

[![GitHub](https://img.shields.io/badge/GitHub-FlavioEugenio-black?style=flat-square&logo=github)](https://github.com/FlavioEugenio)

---

<div align="center">

⭐ Se esse projeto te ajudou ou te inspirou, deixa uma estrela no repositório — significa muito!

</div>
