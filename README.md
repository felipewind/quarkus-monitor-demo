# quarkus-monitor-demo
Demo project to generate metrics from Quarkus, collect them on Prometheus and show the data on Grafana's dashboards

# Grafana

Default username and passord: admin / admin

## Dashboards

### 

https://grafana.com/grafana/dashboards/14370

Add this to the `templating` area so the DS_PROMETHEUS will be already configure.

```json
        {
          "current": {
            "selected": false,
            "text": "Prometheus-App",
            "value": "Prometheus-App"
          },
          "hide": 0,
          "includeAll": false,
          "multi": false,
          "name": "DS_PROMETHEUS",
          "options": [],
          "query": "prometheus",
          "refresh": 1,
          "regex": "",
          "skipUrlSync": false,
          "type": "datasource"
        }
```




