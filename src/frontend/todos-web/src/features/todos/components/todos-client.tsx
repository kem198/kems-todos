"use client";

import { JsonDisplay } from "@/components/display/json-display";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { ItemGroup } from "@/components/ui/item";
import { Separator } from "@/components/ui/separator";
import { TodoItem } from "@/features/todos/components/todo-item";
import { ApiResponseData } from "@/types/common/api-response-data";
import { Todo } from "@/types/todo/todo";
import { PlusIcon } from "lucide-react";

type TodosClientProps = {
  initialTodos: Todo[];
  responseData: ApiResponseData;
};

export function TodosClient({ initialTodos, responseData }: TodosClientProps) {
  return (
    <div className="flex w-full flex-col gap-4">
      {/* <TodosNavigation /> */}
      <div className="flex gap-2">
        <Input type="text" placeholder="Type todo title..." />
        <Button type="submit">
          <PlusIcon />
          Add
        </Button>
      </div>
      <ItemGroup className="gap-4">
        {initialTodos.map((todo) => (
          <TodoItem key={todo.todoId} todo={todo} />
        ))}
      </ItemGroup>

      <Separator />

      <ul className="flex w-full flex-col gap-2">
        <li className="rounded border border-gray-500 p-2">
          <p>Added Task 2</p>
        </li>
      </ul>

      <Separator />

      <JsonDisplay data={responseData} />
    </div>
  );
}
