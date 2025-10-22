"use client";

import { JsonDisplay } from "@/components/display/json-display";
import { Separator } from "@/components/ui/separator";
import { ApiResponseData } from "@/types/common/api-response-data";
import { Todo } from "@/types/todo/todo";

type TodosClientProps = {
  initialTodos: Todo[];
  responseData: ApiResponseData;
};

export function TodosClient({ initialTodos, responseData }: TodosClientProps) {
  return (
    <>
      <ul className="flex w-full flex-col gap-2">
        {initialTodos.map((todo) => (
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
