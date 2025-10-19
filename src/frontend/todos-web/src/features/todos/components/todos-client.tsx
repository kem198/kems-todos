"use client";

import { JsonDisplay } from "@/components/display/json-display";
import { Separator } from "@/components/ui/separator";
import { ApiResponseData } from "@/types/example/common/api-response-data";
import { Todo } from "@/types/todo/todo";
import { useState } from "react";

// propsの型を定義
type TodosClientProps = {
  initialTodos: Todo[];
};

export function TodosClient({ initialTodos }: TodosClientProps) {
  const [todos, setTodos] = useState<Todo[]>(initialTodos);
  const [responseData, setResponseData] = useState<ApiResponseData | undefined>(
    undefined,
  );

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

      <ul className="flex w-full flex-col gap-2">
        <li className="rounded border border-gray-500 p-2">
          <p>Added Task 2</p>
        </li>
      </ul>

      <Separator />

      <JsonDisplay data={responseData} />
    </>
  );
}
