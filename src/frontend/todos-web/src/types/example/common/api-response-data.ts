export type ApiResponseData = {
  status: number;
  statusText: string;
  ok: boolean;
  type: string;
  redirected: boolean;
  url: string;
  headers: Record<string, string>;
  body: string;
  bodyUsed: boolean;
};
