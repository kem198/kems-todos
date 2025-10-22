"use client";

import { JsonDisplay } from "@/components/display/json-display";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardAction,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Separator } from "@/components/ui/separator";
import { ApiResponseData } from "@/types/common/api-response-data";
import { Todo } from "@/types/todo/todo";
import { format } from "date-fns";
import { CheckIcon, PenIcon } from "lucide-react";

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
              <CardAction>
                <Button
                  variant={todo.finished ? "default" : "outline"}
                  size="icon"
                  className="rounded-full"
                >
                  {todo.finished && <CheckIcon />}
                </Button>
              </CardAction>
              <CardTitle>{todo.todoTitle}</CardTitle>
              {todo.todoDescription && (
                <CardDescription>{todo.todoDescription}</CardDescription>
              )}
            </CardHeader>
            <CardFooter className="flex gap-2">
              <Badge variant="secondary">
                <PenIcon />
                {format(todo.createdAt, "yyyy-MM-dd")}
              </Badge>
            </CardFooter>
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
