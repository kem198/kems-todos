"use client";

import { JsonDisplay } from "@/components/display/json-display";
import { Separator } from "@/components/ui/separator";
import { ApiResponseData } from "@/types/example/common/api-response-data";
import { Todo } from "@/types/todo/todo";
import { useEffect, useState } from "react";

export function TodosClient() {
  const [todos, setTodos] = useState<Todo[]>([]);
  const [responseData, setResponseData] = useState<ApiResponseData | undefined>(
    undefined,
  );

  useEffect(() => {
    const fetchTodo = async () => {
      try {
        // TODO: Web サーバ上で実行する
        // TODO: http://localhost:8080 を環境変数にする
        const url = "http://localhost:8080/v1/todos";
        const response = await fetch(url);

        const headers: Record<string, string> = {};
        for (const [key, value] of response.headers) {
          headers[key] = value;
        }

        const respondedTodos = await response.json();
        const responseInfo = {
          status: response.status,
          statusText: response.statusText,
          ok: response.ok,
          type: response.type,
          redirected: response.redirected,
          url: response.url,
          headers,
          body: respondedTodos,
          bodyUsed: response.bodyUsed,
        };

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        setTodos(respondedTodos);
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
      <ul className="flex w-full flex-col gap-2">
        {todos.map((todo) => (
          <li
            key={String(todo.todoId)}
            className="rounded border border-gray-500 p-2"
          >
            <p>{todo.todoTitle}</p>
            <p>{todo.todoDescription}</p>
            <p>{String(todo.finished)}</p>
            <p>{String(todo.createdAt)}</p>
          </li>
        ))}
      </ul>

      <Separator />

      <JsonDisplay data={responseData} />
    </>
  );
}
