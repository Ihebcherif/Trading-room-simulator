export interface Ordre {
    idOrdre?: number;
    typeOrdre: string; // Enum: BUY, SELL
    dateOrdre: string; // Date of the order
    unitPrice: number; // Unit price of the asset
    status: boolean; // Whether the order is executed
    quantity: number; // Quantity involved in the order
    actif: string; // Enum: FOREX, CRYPTO
  }
  