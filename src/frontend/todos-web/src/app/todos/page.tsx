import { Page } from "@/components/layout/page";
import { TypographyH1 } from "@/components/typography/typography-h1";

export default function Home() {
  return (
    <Page>
      <TypographyH1>Todos</TypographyH1>
      <div>
        <p>Added Task 1</p>
        <p>Added Task 2</p>
      </div>
    </Page>
  );
}
