import { AppPage } from "@/components/layout/app-page";
import { TypographyH1 } from "@/components/typography/typography-h1";
import { TodosClient } from "@/features/todos/components/todos-client";
import { getTodos } from "@/features/todos/utils/actions";

export default async function TodosPage() {
  const todos = await getTodos();

  return (
    <AppPage>
      <TypographyH1>Todos</TypographyH1>
      <TodosClient initialTodos={todos} />
    </AppPage>
  );
}
