import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { TablerIconsModule } from 'angular-tabler-icons';
import { MaterialModule } from 'src/app/material.module';
import { MatButtonModule } from '@angular/material/button';



@Component({
  selector: 'app-profit-expenses',
  standalone: true,
  imports: [MaterialModule, TablerIconsModule, MatButtonModule],
  templateUrl: './profit-expenses.component.html',
})
export class AppProfitExpensesComponent implements AfterViewInit {

 constructor() {}

    ngAfterViewInit(): void {
      this.loadTradingViewWidget();
    }
  
    loadTradingViewWidget(): void {
      const script = document.createElement('script');
      script.type = 'text/javascript';
      script.src = 'https://s3.tradingview.com/tv.js';
      script.onload = () => this.initTradingView();
      document.body.appendChild(script);
    }
  
    initTradingView(): void {
      new (window as any).TradingView.widget({
        "container_id": "tradingview_advanced_container",
        "width": "100%",
        "height": 600,
        "symbol": "BINANCE:BTCUSDT",
        "interval": "15",
        "timezone": "Etc/UTC",
        "theme": "light",
        "style": "1",
        "toolbar_bg": "#f1f3f6",
        "allow_symbol_change": true,
        "save_image": false,
        "details": true,
        "withdateranges": true,
        "studies": ["RSI@tv-basicstudies"],
        "enabled_features": ["header_symbol_search", "chart_toolbar", "header_fullscreen_button"],
        "disabled_features": [],
        "studies_overrides": {},
        "overrides": {},
      });
    }
}
