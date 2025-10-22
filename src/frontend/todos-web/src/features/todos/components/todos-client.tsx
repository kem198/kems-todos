"use client";

import { JsonDisplay } from "@/components/display/json-display";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import {
  Item,
  ItemActions,
  ItemContent,
  ItemDescription,
  ItemFooter,
  ItemMedia,
  ItemTitle,
} from "@/components/ui/item";
import { Separator } from "@/components/ui/separator";
import { ApiResponseData } from "@/types/common/api-response-data";
import { Todo } from "@/types/todo/todo";
import { format } from "date-fns";
import { ja } from "date-fns/locale";
import {
  BellIcon,
  CalendarClockIcon,
  CheckIcon,
  ChevronRightIcon,
  MapPinIcon,
} from "lucide-react";

type TodosClientProps = {
  initialTodos: Todo[];
  responseData: ApiResponseData;
};

export function TodosClient({ initialTodos, responseData }: TodosClientProps) {
  return (
    <>
      <div className="flex w-full flex-col gap-4">
        {initialTodos.map((todo) => (
          <Item key={String(todo.todoId)} variant="outline">
            <ItemMedia>
              <Button
                variant={todo.finished ? "default" : "outline"}
                size="icon"
                className="rounded-full"
              >
                {todo.finished && <CheckIcon />}
              </Button>
            </ItemMedia>
            <ItemContent>
              <ItemTitle>{todo.todoTitle}</ItemTitle>{" "}
              {todo.todoDescription && (
                <ItemDescription>{todo.todoDescription}</ItemDescription>
              )}
            </ItemContent>
            <ItemActions>
              <ChevronRightIcon className="size-4" />
            </ItemActions>
            <ItemFooter>
              <div className="flex gap-2">
                <Badge variant="secondary">
                  <CalendarClockIcon />
                  {format(todo.createdAt, "M月d日 (E)", { locale: ja })}
                </Badge>
                <Badge variant="secondary">
                  <BellIcon />3
                </Badge>
                <Badge variant="secondary">
                  <MapPinIcon />
                  Fukuoka, Japan
                </Badge>
              </div>
            </ItemFooter>
          </Item>
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
