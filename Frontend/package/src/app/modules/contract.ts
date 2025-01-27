export interface Contract {
    idContract?: number;
    coverage: number;
    prime: number;
    startDate: string;
    endDate: string;
    policy: string; // Enum: ACTIVE, EXPIRED, PENDING
  }
  