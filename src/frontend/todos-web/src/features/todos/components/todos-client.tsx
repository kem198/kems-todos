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
  ItemGroup,
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
  EllipsisVertical,
  Equal,
  LinkIcon,
  MapPinIcon,
  PlusIcon,
  SquareMousePointer,
} from "lucide-react";

type TodosClientProps = {
  initialTodos: Todo[];
  responseData: ApiResponseData;
};

export function TodosClient({ initialTodos, responseData }: TodosClientProps) {
  return (
    <div className="flex w-full flex-col gap-4">
      <div className="flex gap-2">
        <Button>
          <PlusIcon />
          Add Todo
        </Button>
        <Button variant="outline">
          <SquareMousePointer />
          Edit
        </Button>
        <div className="ml-auto">
          <Button variant="ghost" size="icon-lg" className="rounded-full">
            <EllipsisVertical />
          </Button>
        </div>
      </div>

      <ItemGroup className="gap-4">
        {initialTodos.map((todo) => (
          <Item key={String(todo.todoId)} variant="outline" asChild>
            <a href="#">
              <ItemMedia className="flex items-center">
                <Button
                  variant={todo.finished ? "default" : "outline"}
                  size="icon-sm"
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

              <ItemActions className="self-start">
                <Equal className="size-4 cursor-grab text-gray-400" />
              </ItemActions>

              <ItemFooter className="pl-12">
                <div className="flex gap-2">
                  <Badge variant="outline">
                    <CalendarClockIcon />
                    {format(todo.createdAt, "M月d日 (E)", { locale: ja })}
                  </Badge>
                  <Badge variant="outline">
                    <BellIcon />3
                  </Badge>
                  <Badge variant="outline">
                    <MapPinIcon />
                    Fukuoka, Japan
                  </Badge>
                  <Badge variant="outline">
                    <LinkIcon />
                  </Badge>
                </div>
              </ItemFooter>
            </a>
          </Item>
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
