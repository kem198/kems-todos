"use client";

import { JsonDisplay } from "@/components/display/json-display";
import { ItemGroup } from "@/components/ui/item";
import { Separator } from "@/components/ui/separator";
import { TodoItem } from "@/features/todos/components/todo-item";
import { TodosNavigation } from "@/features/todos/components/todos-navigation";
import { ApiResponseData } from "@/types/common/api-response-data";
import { Todo } from "@/types/todo/todo";

type TodosClientProps = {
  initialTodos: Todo[];
  responseData: ApiResponseData;
};

export function TodosClient({ initialTodos, responseData }: TodosClientProps) {
  return (
    <div className="flex w-full flex-col gap-4">
      <TodosNavigation />
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
