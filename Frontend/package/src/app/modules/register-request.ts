export interface RegisterRequest{
  firstname?:string;
  lastname?:string;
  username?:string;
  password?:string;
  email?:string;
  assure?:boolean;
  role?:string;
  mfaEnabled?:string;
}
