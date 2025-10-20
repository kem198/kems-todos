import { ApiResponseData } from "@/types/common/api-response-data";
import { Todo } from "@/types/todo/todo";

export async function getTodos(): Promise<[Todo[], ApiResponseData]> {
  const apiBaseUrl = process.env.API_BASE_URL;
  if (!apiBaseUrl) {
    throw new Error("API_BASE_URL environment variable is not set.");
  }

  const res = await fetch(`${apiBaseUrl}/v1/todos`, { cache: "no-store" });
  const responseJson = await res.json();

  const responseInfo: ApiResponseData = {
    headers: Object.fromEntries(res.headers.entries()),
    ok: res.ok,
    redirected: false,
    status: res.status,
    statusText: res.statusText,
    type: res.type,
    url: res.url,
    body: responseJson,
    bodyUsed: res.bodyUsed,
  };

  if (!res.ok) {
    throw new Error(`Failed to fetch todos. Status: ${res.status}`);
  }

  try {
    return [responseJson as Todo[], responseInfo];
  } catch {
    throw new Error("Failed to parse todos response as JSON.");
  }
}
