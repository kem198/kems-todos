"use client";

import { JsonDisplay } from "@/components/display/json-display";
import {
  Card,
  CardAction,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
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
      <div className="flex w-full flex-col gap-4">
        {initialTodos.map((todo) => (
          <Card key={String(todo.todoId)}>
            <CardHeader>
              <CardTitle>{todo.todoTitle}</CardTitle>
              <CardDescription>{todo.todoDescription}</CardDescription>
              <CardAction></CardAction>
            </CardHeader>
            <CardContent>
              <p>{todo.finished ? "終わったよ" : "終わってないよ"}</p>
              <p>{String(todo.createdAt)}</p>
            </CardContent>
          </Card>
        ))}
      </div>

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
