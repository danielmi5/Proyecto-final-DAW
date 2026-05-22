export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  name: string;
  email: string;
  password: string;
}

export interface TokenResponse {
  accessToken: string;
  tokenType: string;
  expiresIn: number;
}

export interface AuthResponse {
  id: string;
  name: string;
  email: string;
  role: string;
}

export interface DecodedToken {
  sub: string;
  name?: string;
  roles?: { authority: string }[];
  exp: number;
  iat: number;
  id?: number | null;
}
