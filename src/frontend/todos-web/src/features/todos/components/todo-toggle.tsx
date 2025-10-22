"use client";

import { Toggle } from "@/components/ui/toggle";
import { Todo } from "@/types/todo/todo";
import { CheckIcon } from "lucide-react";
import { useState } from "react";

type TodoToggleProps = {
  todo: Todo;
};

export function TodoToggle({ todo }: TodoToggleProps) {
  const [checked, setChecked] = useState(!!todo.finished);

  return (
    <Toggle
      pressed={checked}
      onPressedChange={setChecked}
      variant="outline"
      className={`data-[state=on]:bg-primary cursor-pointer rounded-full`}
    >
      {checked && <CheckIcon className="text-primary-foreground" />}
    </Toggle>
  );
}
