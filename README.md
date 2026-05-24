# ReportSystem

Sistema de reports avançado para servidores Minecraft Bukkit/Spigot 1.8 desenvolvido em Java.

O plugin foi criado com foco em organização, moderação e gerenciamento de denúncias dentro do servidor, utilizando menus interativos, sistema de logs, filtros e integração com Discord Webhook.

---

# Funcionalidades

## Sistema de Reports
- Reportar jogadores
- Motivo personalizado
- Categoria de report
- Prevenção de auto-report
- Sistema anti-spam
- Cooldown de reports
- Bloqueio de palavras blacklist

---

## Menu GUI
- Interface gráfica interativa
- Visualização de reports
- Filtros por status
- Filtros por categoria
- Sistema de páginas
- Abrir detalhes do report
- Resolver reports
- Deletar reports

---

## Sistema Staff
- Logs administrativos
- Alteração de status
- Histórico de ações
- Permissões separadas

---

## Integração Discord
- Webhook Discord
- Logs automáticos
- Notificações em tempo real

---

## Persistência
- Salvamento em YAML
- Cache de reports
- Carregamento automático
- Salvamento assíncrono

---

# Comandos

| Comando | Função |
|---|---|
| `/report <jogador> <motivo>` | Reportar jogador |
| `/reports` | Abrir painel de reports |
| `/reportstatus` | Alterar status |
| `/reportlogs` | Ver logs |

---

# Permissões

| Permissão | Função |
|---|---|
| `reports.view` | Ver reports |
| `reports.delete` | Deletar reports |
| `reports.status` | Alterar status |

---

# Tecnologias Utilizadas

- Java
- Bukkit API
- Spigot API
- Maven
- YAML
- Discord Webhook

---

# Estrutura do Projeto

```text
src/main/java/com/flavio/reportsystem/

commands/
listeners/
managers/
models/
utils/
```

---

# Estrutura dos Arquivos

## reports.yml
Armazena todos os reports.

## logs.yml
Armazena logs administrativos.

## config.yml
Configurações gerais do plugin.

## plugin.yml
Registro de comandos e permissões.

---

# Configuração

## Webhook Discord

```yaml
webhook:

  enabled: false

  url: "COLE_WEBHOOK_AQUI"
```

---

## Blacklist

```yaml
blacklist:

  - "fdp"
  - "lixo"
  - "hackclient"
```

---

# Funcionalidades do Menu

## Painel Principal
- Todos reports
- Reports abertos
- Reports fechados
- Categorias

## Painel do Report
- Informações completas
- Resolver report
- Deletar report

---

# Categorias

- HACK
- CHAT
- ABUSE

---

# Recursos Técnicos

- Programação orientada a objetos
- Organização em camadas
- Sistema de cache
- Salvamento async
- Menus customizados
- Sistema modular
- Tratamento de erros
- Integração externa

---

# Objetivo do Projeto

Projeto desenvolvido para prática de:
- Java
- Bukkit API
- Manipulação de inventários
- Eventos
- Persistência de dados
- Arquitetura de plugins Minecraft

---

# Autor

Flavio Eugenio
