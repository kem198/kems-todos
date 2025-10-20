import { ApiResponseData } from "@/types/common/api-response-data";

export type GreetingStatus = "pending" | "fulfilled" | "rejected";

export type GreetingResult = {
  status: GreetingStatus;
  greeting: string;
  responseData?: ApiResponseData;
  errorMessage?: string;
};
