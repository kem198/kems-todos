"use client";

import { JsonDisplay } from "@/components/display/json-display";
import { ApiResponseData } from "@/types/example/common/api-response-data";
import { useEffect, useState } from "react";

export function TodosClient() {
  const [todo, setTodo] = useState<string>("");
  const [responseData, setResponseData] = useState<ApiResponseData | undefined>(
    undefined,
  );

  useEffect(() => {
    // TODO: この辺キレイにする
    const fetchTodo = async () => {
      try {
        // TODO: http://localhost:8080 を環境変数にする
        const url = "http://localhost:8080/v1/todos";
        const response = await fetch(url);

        const headers: Record<string, string> = {};
        for (const [key, value] of response.headers) {
          headers[key] = value;
        }

        const todoArray = await response.text();
        const responseInfo = {
          status: response.status,
          statusText: response.statusText,
          ok: response.ok,
          type: response.type,
          redirected: response.redirected,
          url: response.url,
          headers,
          body: todoArray,
          bodyUsed: response.bodyUsed,
        };
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        setTodo(todoArray);
        setResponseData(responseInfo);
      } catch (error_) {
        // TODO: エラー時は画面上に通知する
        console.log(error_);
      }
    };
    fetchTodo();
  }, []);

  return (
    <>
      <p>Added Task 1</p>
      <p>Added Task 2</p>
      <p>{todo}</p>
      <JsonDisplay data={responseData} />
    </>
  );
}
