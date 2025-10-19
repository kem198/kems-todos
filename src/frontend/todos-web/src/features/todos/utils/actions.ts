import { Todo } from "@/types/todo/todo";

export async function getTodos(): Promise<Todo[]> {
  const apiBaseUrl = process.env.API_BASE_URL;
  if (!apiBaseUrl) {
    throw new Error("API_BASE_URL environment variable is not set.");
  }

  const url = `${apiBaseUrl}/v1/todos`;

  try {
    const res = await fetch(url, { cache: "no-store" });

    if (!res.ok) {
      console.error("Failed to fetch todos:", await res.text());
      throw new Error(`Failed to fetch todos. Status: ${res.status}`);
    }

    return res.json();
  } catch (error) {
    console.error("An error occurred while fetching todos:", error);
    throw new Error("Could not fetch todos.");
  }
}
