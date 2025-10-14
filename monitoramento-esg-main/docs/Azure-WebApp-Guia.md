
# Guia Rápido — Deploy no Azure Web App (Containers)

## 1. Criar recursos (CLI)
```bash
az login
az group create -n RG-ESG -l eastus
az appservice plan create -g RG-ESG -n ASP-ESG --is-linux --sku B1
az webapp create -g RG-ESG -p ASP-ESG -n <AZURE_WEBAPP_NAME> --deployment-container-image-name ghcr.io/<org>/<app>:latest
```

## 2. App Settings (portal ou CLI)
- WEBSITES_PORT=8080
- SPRING_PROFILES_ACTIVE=prod
- POSTGRES_DB, POSTGRES_USER, POSTGRES_PASSWORD

## 3. Credenciais do Registry (se imagem privada)
Portal → Web App → Deployment Center → Registry settings (ou CLI).

## 4. GitHub Secrets/Vars
- **Secrets:** AZURE_CREDENTIALS (JSON do Service Principal), REGISTRY_USERNAME, REGISTRY_TOKEN
- **Vars:** REGISTRY, APP_NAME, AZURE_WEBAPP_NAME, POSTGRES_DB, POSTGRES_USER, PROD_URL

## 5. Pipeline
Após `docker_build_push`, o job `deploy_azure` publica a imagem no Web App.

## 6. Evidências
- Print do job `deploy_azure` verde
- URL do Web App abrindo `/health` ou `/actuator/health`
```

